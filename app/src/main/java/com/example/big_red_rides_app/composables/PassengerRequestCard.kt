package com.example.big_red_rides_app.composables

import androidx.compose.foundation.background
import androidx.compose.material.icons.filled.Close
import com.example.big_red_rides_app.rides.RideRequest
import com.example.big_red_rides_app.rides.mockRides
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.big_red_rides_app.R
import com.example.big_red_rides_app.rides.RequestStatus
import com.example.big_red_rides_app.rides.Ride
import com.example.big_red_rides_app.rides.mockProfiles
import com.example.big_red_rides_app.rides.mockRideRequests

@Composable
fun PasssengerRequestCard(
    request: RideRequest,
    onClick: () -> Unit
) {
    val ride = mockRides.find { it.id == request.rideId }
    val driverProfile = ride?.let { mockProfiles.find { profile -> profile.id == it.driverId } }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ){
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.weight(1f)
            ){
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 4.dp)
                    ){
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = ride!!.departureTime,
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Icon(
                                painter = painterResource(R.drawable.arrowdes),
                                contentDescription = "arrow",
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = ride!!.arrivalTime,
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(modifier = Modifier.weight(1f))
                            Text(
                                text = "$${ride.price}",
                                style = MaterialTheme.typography.titleMedium,
                                color = Color.Red,
                                fontWeight = FontWeight.ExtraBold

                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ){
                    ProfilePic(driverProfile!!.name)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = driverProfile.name ,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Icon(
                        painter = painterResource(R.drawable.ppl),
                        contentDescription = "Seats",
                        modifier = Modifier.size(24.dp)
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = "${ride.availableSeats}",
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(2.dp))
                Row {
                    Text(
                        text = "Status: ",
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = request.status.toString(),
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold,
                        color = (
                                if (request.status == RequestStatus.ACCEPTED) {
                                    Color.Green
                                } else if (request.status == RequestStatus.REQUESTED){
                                    Color.Blue
                                } else {
                                    Color.Red
                                }
                                )
                    )
                }

            }
            Spacer(modifier = Modifier.width(10.dp))
            Icon(
                painter = painterResource(R.drawable.cancel),
                contentDescription = "Cancel",
                modifier = Modifier
                    .size(20.dp)
                    .clickable { onClick() } //cancel ride
            )
        }
    }
}

@Preview
@Composable
fun PreviewPassengerRequestCard(){
    PasssengerRequestCard(
        request = mockRideRequests[0],
        onClick = {}
    )
}