package com.example.big_red_rides_app.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.big_red_rides_app.retrofit.RideDisplay
import com.example.big_red_rides_app.retrofit.RideRequest


@Composable
fun DriverRequestCard(
    requestDisplay: RideDisplay,
    onAcceptClicked: (RideRequest) -> Unit
){
    val request = requestDisplay.request
    val passenger = requestDisplay.passenger
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProfilePic(passenger.name)
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = passenger.name,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = " has requested to ride with you.",
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2
                )

            }
        }
        Spacer(modifier = Modifier.width(8.dp))
        if (request.status == "pending") {
            Button(
                onClick = { onAcceptClicked(request) },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD15429)),
                modifier = Modifier.width(90.dp).height(36.dp)

            ) {
                Text(
                    text = "Accept",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        } else {
            Button(
                onClick = {},
                enabled = false,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray),
                modifier = Modifier.height(36.dp).width(80.dp)
            ){
                Text(
                    text = "Accepted",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

