package com.example.big_red_rides_app.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.big_red_rides_app.R

@Composable
fun AppHeader(
    onClick: () -> Unit
){
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically){
            Image(
                painter = painterResource(id = R.drawable.bear),
                contentDescription = "Bear logo",
                modifier = Modifier
                    .height(38.dp)
                    .width(40.dp)
                    .padding(end = 8.dp)
            )
            Row {
                Text(
                    text = "Red",
                    style = TextStyle(
                        fontSize = 30.sp,
                        //fontFamily = FontFamily(Font(R.font.inter)),
                        fontWeight = FontWeight(700),
                        color = Color(0xFFD15429),
                    )
                )
                Text(
                    text = "Rides",
                    style = TextStyle(
                        fontSize = 30.sp,
                        //fontFamily = FontFamily(Font(R.font.inter)),
                        fontWeight = FontWeight(700),
                        color = Color.Black,
                    )
                )
            }
        }


        IconButton(onClick = onClick) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                contentDescription = "Log out" //takes you to login screen
            )
        }
    }
}