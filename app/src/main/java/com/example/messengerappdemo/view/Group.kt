package com.example.messengerappdemo.view

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.messengerappdemo.model.SampleData
import java.text.SimpleDateFormat
import com.example.messengerappdemo.R
import com.example.messengerappdemo.ui.theme.MainError
import com.example.messengerappdemo.ui.theme.MainGreen
import com.example.messengerappdemo.ui.theme.liteGrayColor
import java.util.*

// GROUP PAGE MAIN STRUCTURE
@SuppressLint("SimpleDateFormat")
@Composable
fun GroupListScreen() {
    val date = SimpleDateFormat("hh:mm a")
    val strDate: String = date.format(Date())

    val listOfStatusData = listOf(
        SampleData("Grup 1", "text 1", "Sample Url", strDate),
        SampleData("Grup 2", "text 2", "Sample Url", strDate),
        SampleData("Grup 3", "text 3", "Sample Url", strDate),
        SampleData("Grup 4", "text 4", "Sample Url", strDate),
        SampleData("Grup 5", "text 5", "Sample Url", strDate),
        SampleData("Grup 6", "text 5", "Sample Url", strDate)
    )

    val listOfViewedData = listOf(
        SampleData("Grup 1", "text 1", "Sample Url", strDate),
        SampleData("Grup 2", "text 2", "Sample Url", strDate),
        SampleData("Grup 3", "text 3", "Sample Url", strDate),
        SampleData("Grup 4", "text 4", "Sample Url", strDate),
        SampleData("Grup 5", "text 5", "Sample Url", strDate)
    )
    LazyColumn(
        modifier = Modifier
            .background(Color.White)
    ) {
        items(listOfStatusData.size) { index ->
            SampleStatusListItem(listOfStatusData[index])
        }

    }
}

@Composable
fun SampleStatusListItem(data: SampleData) {
    Card(
        modifier = Modifier
            .padding(top = 8.dp, bottom = 4.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .wrapContentHeight(align = Alignment.Top)
            .size(width = 200.dp, height = 100.dp),
        elevation = 5.dp,
        shape = RoundedCornerShape(20.dp),
        backgroundColor = Color.White
    ){
    Column {
        Row(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_run_circle_24),
                contentDescription = "Status Image",
                modifier = Modifier
                    .width(70.dp)
                    .height(70.dp)
                    .padding(5.dp)
            )
            Column(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = data.name,
                    fontSize = 15.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.padding(3.dp))
                Text(
                    text = "Bugün, ${data.createdDate}",
                    fontSize = 15.sp,
                    color = Color.Gray,
                )
            }
        }
    }
    }
}

@Preview(showBackground = true)
@Composable
fun ChatPreview(){
    GroupListScreen()
}








