package com.example.big_red_rides_app.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.unit.dp
import com.example.big_red_rides_app.rides.RequestStatus
import com.example.big_red_rides_app.rides.RideRequest

@Composable
fun DriverRequestCard(
    request: RideRequest,
    onAcceptClicked: (RideRequest) -> Unit
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            ProfilePic(request.passengerName)
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = "${request.passengerName} has requested to ride with you.",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
        if (request.status == RequestStatus.REQUESTED) {
            Button(
                onClick = { onAcceptClicked(request) },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE64A19))

            ) {
                Text(
                    text = "Accept"
                )
            }
        } else {
            Button(
                onClick = {},
                enabled = false,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
            ){
                Text(
                    text = "Accepted"
                )
            }
        }
    }
}