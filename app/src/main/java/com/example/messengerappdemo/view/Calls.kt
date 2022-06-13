package com.example.messengerappdemo.view

import android.annotation.SuppressLint
import android.provider.CallLog.Calls
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.messengerappdemo.model.SampleData
import com.example.messengerappdemo.R
import com.example.messengerappdemo.model.UserProfile
import java.text.SimpleDateFormat
import java.util.*

/*
@SuppressLint("SimpleDateFormat")
@Composable
fun Calls() {
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
    ) {
        LazyColumn {
            items(listOfData.size) { index ->
                CallList(listOfData[index], index)
            }
        }
    }
}*/

@Composable
fun CallListScreen(userProfiles: List<UserProfile>, navController: NavHostController){

    Surface(
        modifier = Modifier.fillMaxSize()
    ){
        LazyColumn{
            items(userProfiles){
                    userProfile -> Calls(userProfile = userProfile){
                navController?.navigate("user_calls/${userProfile.id}")
            }
            }
        }
    }
/**/
}

@Composable
fun Calls(/*sampleData: SampleData, index: Int*/ userProfile: UserProfile, clickAction:() -> Unit) {
    /*
    Column(
        modifier = Modifier.padding(5.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_run_circle_24),
                contentDescription = "User Image",
                modifier = Modifier
                    .width(70.dp)
                    .height(70.dp)
                    .padding(5.dp)
                    .weight(0.2f)
            )
            Column(
                modifier = Modifier
                    .padding(5.dp)
                    .weight(0.7f)
            ) {
                Text(
                    text = sampleData.name,
                    fontSize = 15.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.padding(5.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(
                            if (index % 2 == 0) {
                                R.drawable.ic_income_call
                            } else {
                                R.drawable.ic_outgoing_call
                            }
                        ),
                        contentDescription = "Income & Outgoing call icons",
                        modifier = Modifier
                            .width(20.dp)
                            .height(20.dp)
                            .padding(0.dp, 0.dp, 3.dp, 0.dp)
                    )

                    Text(
                        text = "Bugün, ${sampleData.createdDate}",
                        fontSize = 15.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Image(
                painter = painterResource(
                    if (index % 2 == 0) {
                        R.drawable.ic_baseline_call_24
                    } else {
                        R.drawable.ic_baseline_videocam_24
                    }
                ),
                contentDescription = "Income & Outgoing call icons",
                modifier = Modifier
                    .width(30.dp)
                    .height(30.dp)
                    .padding(5.dp)
                    .weight(0.2f)
            )
        }
    }
    */

    Column(
        modifier = Modifier.padding(5.dp)
    ) {
        Card(
            modifier = Modifier
                .padding(top = 2.dp, bottom = 2.dp, start = 10.dp, end = 2.dp)
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
                /*.clickable {
                           navController.navigate("app_chat")
                },
                 */
                verticalAlignment = Alignment.CenterVertically
            ) {
                ProfilePicture(userProfile.pictureUrl, userProfile.status,50.dp)
                Column(
                    modifier = Modifier
                        .padding(5.dp)
                        .weight(0.7f)
                ) {
                    Text(
                        text = userProfile.name,
                        fontSize = 15.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.padding(5.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(
                                if (userProfile.status ){
                                    R.drawable.ic_income_call

                                }else{R.drawable.ic_outgoing_call}
                            ) ,
                            contentDescription = "income call",
                            modifier = Modifier
                                .width(20.dp)
                                .height(20.dp)
                                .padding(0.dp, 0.dp, 3.dp, 0.dp)
                        )
                        Text(
                            text = "Today, ${userProfile.date}",
                            fontSize = 15.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                Image(
                    painter = painterResource(
                        if (userProfile.status){
                            R.drawable.ic_baseline_call_24

                        }else{R.drawable.ic_baseline_videocam_24}
                    ) ,
                    contentDescription = "income call",
                    modifier = Modifier
                        .width(40.dp)
                        .height(40.dp)
                        .defaultMinSize(minWidth = 5.dp, minHeight = 5.dp)
                        .padding(top = 0.dp, bottom = 0.dp, start = 0.dp, end = 15.dp)
                )
            }
        }

    }
}