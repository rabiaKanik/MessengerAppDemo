package com.example.messengerappdemo.view

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.messengerappdemo.R
import com.example.messengerappdemo.Tabs
import com.example.messengerappdemo.TabsContent
import com.example.messengerappdemo.model.SampleData
import com.example.messengerappdemo.model.UserProfile
import com.example.messengerappdemo.model.userProfileList
import com.example.messengerappdemo.ui.theme.liteGrayColor
import java.text.SimpleDateFormat
import com.google.accompanist.pager.*
import java.util.*

/*
@SuppressLint("SimpleDateFormat")
@Composable
fun Chats(navController: NavHostController) {
   val date = SimpleDateFormat("hh:mm a")
    val strDate: String = date.format(Date())

    val listOfData = listOf(
        SampleData("Name 1", "Make It Easy Sample 1", "Sample Url", strDate),
        SampleData("Name 2", "Make It Easy Sample 2", "Sample Url", strDate),
        SampleData("Name 3", "Make It Easy Sample 3", "Sample Url", strDate),
        SampleData("Name 4", "Make It Easy Sample 4", "Sample Url", strDate),
        SampleData("Name 5", "Make It Easy Sample 5", "Sample Url", strDate),
        SampleData("Name 6", "Make It Easy Sample 6", "Sample Url", strDate),
        SampleData("Name 7", "Make It Easy Sample 7", "Sample Url", strDate),
        SampleData("Name 8", "Make It Easy Sample 8", "Sample Url", strDate),
        SampleData("Name 9", "Make It Easy Sample 9", "Sample Url", strDate),
        SampleData("Name 10", "Make It Easy Sample 10", "Sample Url", strDate)
    )

   Column(
       modifier = Modifier
           .fillMaxSize()
           .background(Color.White)
           .wrapContentSize(Alignment.Center)
   ) {
       LazyColumn(
           modifier = Modifier
       ) {
           items(listOfData.size) { index ->
               SampleDataListItem(listOfData[index], navController)
           }
       }
   }
}
*/
@Composable
fun UserListScreen(userProfiles: List<UserProfile>, navController: NavHostController?){

    Surface(
        modifier = Modifier.fillMaxSize()
    ){
        LazyColumn{
            items(userProfiles){
                    userProfile -> ProfileCard(userProfile = userProfile){
                navController?.navigate("user_chat/${userProfile.id}")
            }
            }
        }
    }

}


@Composable
fun  ProfileCard(userProfile: UserProfile, clickAction:() -> Unit) {
    Card(
        modifier = Modifier
            .padding(top = 8.dp, bottom = 4.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .wrapContentHeight(align = Alignment.Top)
            .clickable(onClick = { clickAction.invoke() })
            .size(width = 200.dp, height = 100.dp),
        elevation = 5.dp,
        shape = RoundedCornerShape(20.dp),
        backgroundColor = Color.White
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,

        ) {
            ProfilePicture(userProfile.pictureUrl, userProfile.status,50.dp)
            ProfileContent(userProfile.name, userProfile.status,Alignment.Start, userProfile.date)

        }
    }
}


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
            .padding(12.dp),
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

@Composable
fun ProfileContent(userName: String, onlineStatus: Boolean, alignment: Alignment.Horizontal, date:String) {
    Column(
        modifier = Modifier
            .padding(8.dp),
        horizontalAlignment = alignment
    ){
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = userName,
                modifier = Modifier
                    .weight(1.0f),
                fontSize = 15.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = date,
                fontSize = 13.sp,
                modifier = Modifier.padding(end = 5.dp)
            )

        }
        Text(
            text = "Selam",
            fontSize = 15.sp,
            color = Color.Black,
            fontWeight = FontWeight.Normal
        )
    }
}
/*
@Composable
fun SampleDataListItem(data: SampleData, navController: NavHostController) {
    Column(
        modifier = Modifier.padding(5.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    navController.navigate("whats_app_chat")
                }
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_run_circle_24), //i dont have image url so i put sample in vector image
                contentDescription = "Image",
                modifier = Modifier
                    .width(70.dp)
                    .height(70.dp)
                    .padding(5.dp)
                    .clip(RoundedCornerShape(5.dp))
            )

            Column(
                modifier = Modifier.padding(10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = data.name,
                        modifier = Modifier
                            .weight(1.0f),
                        fontSize = 15.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = data.createdDate,
                        modifier = Modifier
                            .weight(0.5f),
                        fontSize = 12.sp,
                        color = Color.Gray,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.End
                    )
                }

                Spacer(modifier = Modifier.padding(5.dp))

                Text(
                    text = data.desc,
                    fontSize = 15.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Normal
                )

                Spacer(modifier = Modifier.padding(5.dp))

                Divider(color = liteGrayColor, thickness = 1.dp)
            }
        }
    }
}
*/

@Preview(showBackground = true)
@Composable
fun ChatsPreview() {
    UserListScreen(userProfiles = userProfileList, navController = NavHostController(LocalContext.current))

}
