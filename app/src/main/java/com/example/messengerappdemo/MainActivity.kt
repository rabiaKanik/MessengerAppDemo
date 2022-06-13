package com.example.messengerappdemo

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavArgument
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.messengerappdemo.model.UserProfile
import com.example.messengerappdemo.model.userProfileList
import com.example.messengerappdemo.ui.theme.AppCloneTheme
import com.example.messengerappdemo.ui.theme.MessengerAppDemoTheme
import com.example.messengerappdemo.ui.theme.WhatsAppFloatIconColor
import com.example.messengerappdemo.ui.theme.WhatsAppThemeColor
import com.example.messengerappdemo.utils.Constants._tabCurrentStatus
import com.example.messengerappdemo.utils.Constants.tabCurrentStatus
import com.example.messengerappdemo.view.*
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppCloneTheme{
                val navController = rememberNavController()
                val userProfiles: List<UserProfile> = userProfileList
                NavHost(
                    navController = navController,
                    startDestination = "whats_app_main"
                ) {
                    composable("whats_app_main") { MainAppActivity(navController) }
                    //composable("whats_app_chat") { ChatList(navController) }
                    composable(
                        route = "user_chat/{userId}",
                        arguments = listOf(navArgument("userId") {
                            type = NavType.IntType
                        })
                    ) { navBackStackEntry ->
                        ChatList(navBackStackEntry.arguments!!.getInt("userId"), navController)
                    }
                    composable("users_list"){
                        UserListScreen(userProfiles, navController)
                    }
                    //composable("whats_app_detail/{userId}") { UserProfileDetailsScreen(navController) }

                    composable(
                        route = "whats_app_detail/{userId}",
                        arguments = listOf(navArgument("userId"){
                            type = NavType.IntType
                        })
                    ){ navBackStackEntry ->
                        UserProfileDetailsScreen(navBackStackEntry.arguments!!.getInt("userId"), navController)
                    }
                }
            }

        }
    }
}

@ExperimentalPagerApi
@Composable
fun MainAppActivity(navController: NavHostController) {
    val context = LocalContext.current
    val menuExpanded = remember { mutableStateOf(false) }
    val tabStatus = tabCurrentStatus.observeAsState()

    val topBar: @Composable () -> Unit = {
        TopAppBar(
            title = {
                Text(
                    text = "ChatApp",
                    color = Color.White,
                    fontSize = 20.sp
                )
            } ,

            actions = {
                IconButton(
                    onClick = {
                        Toast.makeText(context, "Clicked Search", Toast.LENGTH_SHORT).show()
                    }
                ) {
                    Icon(
                        Icons.Filled.Search,
                        contentDescription = "Search",
                        tint = Color.White
                    )
                }

                IconButton(
                    onClick = {
                        menuExpanded.value = true
                    }
                ) {
                    Icon(
                        Icons.Filled.MoreVert,
                        contentDescription = "Menu",
                        tint = Color.White
                    )
                }

                Column(
                    modifier = Modifier
                        .wrapContentSize(Alignment.TopStart)
                ) {
                    DropdownMenu(
                        modifier = Modifier
                            .width(200.dp)
                            .wrapContentSize(Alignment.TopStart),
                        expanded = menuExpanded.value,
                        onDismissRequest = {
                            menuExpanded.value = false
                        }
                    ) {
                        when(tabStatus.value) {
                            0 -> {
                                DropdownMenuItem(onClick = { /*Handle New group*/ }) {
                                    Text(text = "Yeni grup")
                                }
                                DropdownMenuItem(onClick = { /*Handle New broadcast*/ }) {
                                    Text(text = "Yeni Durum")
                                }
                                DropdownMenuItem(onClick = { /*Handle Settings*/ }) {
                                    Text(text = "Ayarlar")
                                }
                            }
                            1 -> {
                                DropdownMenuItem(onClick = { /*Handle Status privacy*/ }) {
                                    Text(text = "Durum giziliği")
                                }
                                DropdownMenuItem(onClick = { /*Handle Settings*/ }) {
                                    Text(text = "Ayarlar")
                                }
                            }
                            2 -> {
                                DropdownMenuItem(onClick = { /*Handle Clear call log*/ }) {
                                    Text(text = "Arama geçmişini sil")
                                }
                                DropdownMenuItem(onClick = { /*Handle Settings*/ }) {
                                    Text(text = "Ayarlar")
                                }
                            }
                        }
                    }
                }

            },
            backgroundColor = WhatsAppThemeColor,
            elevation = AppBarDefaults.TopAppBarElevation
        )
    }

    Scaffold(
        topBar = {
            topBar()
        },
        content = {
            WhatsAppTab(navController)
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    Toast.makeText(context, "Message Clicked", Toast.LENGTH_SHORT).show()
                },
                backgroundColor = WhatsAppFloatIconColor,
                elevation = FloatingActionButtonDefaults.elevation(),
                modifier = Modifier.padding(10.dp)
            ) {
                when(tabStatus.value) {
                    0 -> {
                        Icon(
                            painterResource(id = R.drawable.ic_chat),
                            contentDescription = "Message",
                            tint = Color.White,
                            modifier = Modifier.padding(10.dp)
                        )
                    }
                    1 -> {
                        Icon(
                            painterResource(id = R.drawable.ic_camera),
                            contentDescription = "Camera",
                            tint = Color.White,
                            modifier = Modifier.padding(10.dp)
                        )
                    }
                    2 -> {
                        Icon(
                            painterResource(id = R.drawable.ic_call),
                            contentDescription = "Add Call",
                            tint = Color.White,
                            modifier = Modifier.padding(10.dp)
                        )
                    }
                }
            }
        }
    )
}

@ExperimentalPagerApi
@Composable
fun WhatsAppTab(navController: NavHostController) {
    val pagerState = rememberPagerState(pageCount = 3)
    Column {
        Tabs(pagerState)
        TabsContent(pagerState, navController)
    }
}

@ExperimentalPagerApi
@Composable
fun Tabs(pagerState: PagerState) {
    val list = listOf("SOHBET", "DURUM", "ARAMA")
    val scope = rememberCoroutineScope()
    _tabCurrentStatus.value = pagerState.currentPage

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = WhatsAppThemeColor,
        contentColor = Color.Gray,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                height = 3.dp,
                color = Color.White
            )
        }
    ) {
        list.forEachIndexed { index, _ ->
            Tab(
                text = {
                    Text(
                        list[index],
                        color = Color.White
                    )
                },
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }
            )
        }
    }
}

@ExperimentalPagerApi
@Composable
fun TabsContent(pagerState: PagerState, navController: NavHostController) {
    val userProfiles: List<UserProfile> = userProfileList
    HorizontalPager(state = pagerState) { page ->
        when(page) {
            0 -> UserListScreen(userProfiles,navController)
            1 -> Status()
            2 -> Calls()
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MessengerAppDemoTheme {
        MainAppActivity(navController = NavHostController(LocalContext.current))
    }
}