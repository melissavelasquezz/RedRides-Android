package com.example.big_red_rides_app.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.big_red_rides_app.composables.ProfilePic
import com.example.big_red_rides_app.rides.mockProfiles

@Composable
fun DetailScreen(
    navController: NavController,
    detailsViewModel: DetailsViewModel = hiltViewModel()
){
    val uiState = detailsViewModel.uiStateFlow.collectAsState().value

    if (uiState.loading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            CircularProgressIndicator()
        }
        return
    }

    val ride = uiState.ride
    if (ride == null) {
        Text("Ride not found. Try again later.")
        return
    }

    val driverProfile = mockProfiles.find { it.id == ride.driverId}

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFAF1E6))
            .padding(24.dp)
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(
                onClick= {navController.popBackStack()}
            ) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back")
            }
            Spacer(modifier = Modifier.height(8.dp))
            ProfilePic(driverProfile!!.name)
            Spacer(modifier = Modifier.height(16.dp))
            Column {
                Text(
                    text = driverProfile.name,
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = driverProfile.gradYear.toString(),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Contact: ${driverProfile!!.email}\n${driverProfile.phone}",
            style = MaterialTheme.typography.bodySmall
        )
        Spacer(modifier = Modifier.height(24.dp))
        HorizontalDivider(color = Color.White, thickness = 2.dp)
        Spacer(modifier = Modifier.height(24.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = "Bus Icon",
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Departing ride • ${ride.date}",
                style = MaterialTheme.typography.titleMedium
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically){
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "person",
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text("${ride.availableSeats}")
        }
        Spacer(modifier = Modifier.height(24.dp))

        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = ride.departureTime,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = ride.departureCity,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = ride.arrivalTime,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = ride.arrivalCity,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            if (ride.price > 0) {
                Text(
                    text = "$${ride.price}",
                    style = MaterialTheme.typography.headlineMedium
                )
            }
             Button(
                 onClick = { detailsViewModel.requestRide() },
                 enabled = !uiState.rideRequested,
                 shape = RoundedCornerShape(24.dp),
                 colors = ButtonDefaults.buttonColors(
                     containerColor = if (uiState.rideRequested) Color.Gray else Color(0xFFE64A19)
                 ),
                 modifier = Modifier.height(50.dp)
             ) {
                 if (uiState.rideRequested){
                     Text(
                         text = "Requested"
                     )
                 } else {
                     Text(
                         text = "Request"
                     )
                 }
             }
        }



    }
}