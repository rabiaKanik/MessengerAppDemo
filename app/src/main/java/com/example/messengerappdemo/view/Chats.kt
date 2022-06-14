package com.example.messengerappdemo.view


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.messengerappdemo.model.UserProfile
import com.example.messengerappdemo.model.userProfileList
import com.example.messengerappdemo.ui.theme.MainError
import com.example.messengerappdemo.ui.theme.MainGreen



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
                MainGreen
            else MainError
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
            text = "Selam!",
            fontSize = 15.sp,
            color = Color.Black,
            fontWeight = FontWeight.Normal
        )

    }
}

@Preview(showBackground = true)
@Composable
fun ChatsPreview() {
    UserListScreen(userProfiles = userProfileList, navController = NavHostController(LocalContext.current))

}
