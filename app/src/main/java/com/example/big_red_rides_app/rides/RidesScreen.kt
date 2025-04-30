package com.example.big_red_rides_app.rides

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.big_red_rides_app.Screen
import com.example.big_red_rides_app.composables.AppHeader
import com.example.big_red_rides_app.composables.DriverRequestCard

@Composable
fun RidesScreen(
    driverRides: List<Ride>,
    rideRequests: List<RideRequest>,
    onAcceptClicked: (RideRequest) -> Unit,
    navController: NavController
){
    Column {
        AppHeader(onClick = {navController.navigate(Screen.LoginScreen)})
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(driverRides.size) { index  ->
                val ride = driverRides[index]
                var expand by remember { mutableStateOf(true) }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { expand= !expand }
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "${ride.date} Trip to ${ride.arrivalCity}",
                            style = MaterialTheme.typography.titleMedium
                        )
                        //icon up or down
                    }
                    HorizontalDivider(color = Color.Red, modifier = Modifier.height(2.dp))

                    if (expand) {
                        val requestsForRide = rideRequests.filter { it.rideId == ride.id}
                        if (requestsForRide.isEmpty()) {
                            Text(
                                text = "No requests yet.",
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.padding(8.dp)
                            )
                        } else {
                            requestsForRide.forEach{
                                    request ->
                                DriverRequestCard(
                                    request = request,
                                    onAcceptClicked = onAcceptClicked
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }

}