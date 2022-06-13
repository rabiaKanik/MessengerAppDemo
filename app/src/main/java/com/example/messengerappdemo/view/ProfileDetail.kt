package com.example.messengerappdemo.view

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.messengerappdemo.model.userProfileList
import com.example.messengerappdemo.ui.theme.WhatsAppFloatIconColor
import com.example.messengerappdemo.ui.theme.WhatsAppThemeColor


@Composable
fun UserProfileDetailsScreen(userId :Int,navController: NavHostController?){

    val userProfile = userProfileList.first { userProfile -> userId == userProfile.id }
    Scaffold( topBar = {
        TopAppBar(
            title = {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    IconButton(onClick = { navController?.navigateUp() })
                    {
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = "Back Arrow",
                            tint = Color.White
                        )
                    }
                    Column(
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            text = "Kişi Bilgisi",
                            color = Color.White,
                            fontSize = 13.sp
                        )
                    }
                }
            },
            backgroundColor = WhatsAppThemeColor)
    }){
        Surface(
            modifier = Modifier.fillMaxSize()
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                ProfilePicture("https://images.unsplash.com/photo-1530577197743-7adf14294584?ixid=MXwxMjA3fDB8MHxzZWFyY2h8NTV8fHBvcnRyYWl0fGVufDB8MnwwfA%3D%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60", true,240.dp)
                ProfileButtonAction()
                ProfileNotification()
                ProfileChildNotification()
            }
        }
    }
}
@Composable
fun ProfileNotification(){

    Card(
        modifier = Modifier
            .padding(25.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(0.dp),
        elevation = 2.dp,
    )
    {
        Column() {
            val checkedState = remember { mutableStateOf(true) }
            Row(
                Modifier
                    .padding(start = 15.dp)
                    .clickable { },
            ) {
                Icon(

                    Icons.Filled.Notifications,
                    contentDescription = "Notification",
                    modifier = Modifier.padding(start = 25.dp, top = 15.dp, bottom = 5.dp),
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))

                Text(
                    modifier = Modifier.padding(top = 15.dp, start = 15.dp),
                    text = "Bildirimleri sesize al"

                )
                Switch(
                    modifier = Modifier.padding(start = 50.dp, top = 5.dp),
                    checked = checkedState.value,
                    onCheckedChange = { checkedState.value = it },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = WhatsAppFloatIconColor
                    )
                )
            }
            Row(
                Modifier
                    .padding(start = 15.dp)
                    .clickable { },
            ) {
                Icon(
                    Icons.Filled.MusicNote,
                    contentDescription = "Notification",
                    modifier = Modifier.padding(start = 25.dp, top = 5.dp, bottom = 5.dp),
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))

                Text(
                    modifier = Modifier.padding(top = 5.dp, start = 15.dp),
                    text= "Özel Bildirimler"
                )

            }
            Row(
                Modifier
                    .padding(start = 15.dp)
                    .clickable { },
            ) {
                Icon(

                    Icons.Filled.Image,
                    contentDescription = "Media",
                    modifier = Modifier.padding(start = 25.dp, top = 15.dp, bottom = 5.dp),
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))


                Text(
                    modifier = Modifier.padding(top = 15.dp, start = 15.dp, bottom = 5.dp),
                    text="Medya görünürlüğü")

            }
        }
    }
}

@Composable
fun ProfileButtonAction(){
    Row(
        Modifier
            .padding(1.dp)

    ) {
        Button(
            colors = ButtonDefaults.buttonColors(backgroundColor = WhatsAppFloatIconColor),
            onClick = {
                // do something here
            },

            modifier = Modifier.size(width = 100.dp,height = 60.dp)
        ) {
            Column(
                modifier = Modifier.padding(11.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Filled.Call,
                    tint = Color.White,
                    contentDescription = "Localized description"
                )
                //Text(text = "Sesli")
            }

        }
        Spacer(modifier = Modifier.width(10.dp))
        Button(
            colors = ButtonDefaults.buttonColors(backgroundColor = WhatsAppFloatIconColor),onClick = {
                // do something here
            },
            modifier = Modifier.size(width = 100.dp,height = 60.dp)
        ) {
            Column(
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Filled.Videocam,
                    tint = Color.White,                    contentDescription = "Localized description"
                )
                //Text(text = "Görüntülü")
            }
        }

        Spacer(modifier = Modifier.width(10.dp))
        Button(
            colors = ButtonDefaults.buttonColors(backgroundColor = WhatsAppFloatIconColor),onClick = {
                // do something here
            },
            modifier = Modifier.size(width = 100.dp,height = 60.dp)
        ) {
            Column(
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Localized description",
                    tint = Color.White
                )
                //Text(text = "Ara")
            }
        }
    }
}

@Composable
fun ProfileChildNotification(){
    Card(
        modifier = Modifier
            //.padding(2.dp)
            .clickable { }
            .padding(start = 27.dp, end = 24.dp),

        elevation = 2.dp,
        shape = RoundedCornerShape(0.dp)
    ) {
        Row(
            Modifier
                .padding(start = 15.dp)
        ) {
            Icon(
                modifier = Modifier.padding(start = 23.dp, top = 25.dp),
                imageVector = Icons.Filled.Lock,
                contentDescription = "Localized description"
            )
            Column(
                modifier = Modifier.padding(15.dp)

            ) {

                Text(
                    buildAnnotatedString {
                        append("Şifreleme")
                    }
                )
                Text(
                    buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(fontWeight = FontWeight.Light, fontSize = 15.sp, color = Color.Gray)
                        ) {
                            append("Mesajlar ve aramalar uçtan uca şifrelidir. ")
                            append(" Doğrulamak için dokunun")
                        }

                    }
                )
            }
        }
    }
}
/*
@Composable
fun ProfilePicture(pictureUrl: String, onlineStatus: Boolean, imageSize: Dp) {
    Card(
        shape = CircleShape,
        border = BorderStroke(
            width = 1.dp,
            color = if (onlineStatus)
                Color.Green
            else Color.Red
        ),
        modifier = Modifier
            .padding(12.dp)
            .padding(top = 25.dp),
        elevation = 4.dp
    ) {
        Image(
            painter = rememberImagePainter(
                data = pictureUrl,
                builder = {
                    transformations(CircleCropTransformation())
                },
            ),
            modifier = Modifier.size(imageSize),
            contentDescription = "Profile picture description",
        )
    }
}



@Preview(showBackground = true)
@Composable
fun UserProfileDetailsScreenPreview() {
    }
*/