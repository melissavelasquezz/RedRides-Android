package com.example.big_red_rides_app.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.big_red_rides_app.R
import com.example.big_red_rides_app.retrofit.User

@Composable
fun PersonalGreeting(
    passenger: User,
    isDriver: Boolean
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFFAF1E6))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.bear),
            contentDescription = "Bear logo",
            modifier = Modifier
                .height(80.dp).width(80.dp)
                .padding(end = 12.dp)
        )
        Row(verticalAlignment = Alignment.CenterVertically){

            Column {
                Text(
                    text = "Hi ${passenger.name},",
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 20.sp,
                    color = Color.Black,
                )
                Text(
                    text = if (isDriver) {
                        "The rides you are offering and requests to join them are below."
                    } else {
                        "The rides you have requested and their status are below."
                    },
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 16.sp,
                    color = Color.Black,
                    )
            }
        }
    }
}