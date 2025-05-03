package com.example.big_red_rides_app.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.big_red_rides_app.R
import com.example.big_red_rides_app.Screen
import com.example.big_red_rides_app.composables.AppHeader
import com.example.big_red_rides_app.composables.ProfilePic
import com.example.big_red_rides_app.retrofit.Ride
import com.example.big_red_rides_app.retrofit.User


@Composable
fun DetailScreen(
    navController: NavController,
    viewModel: DetailsViewModel = hiltViewModel(),
){
    var requestSent by remember { mutableStateOf(false) }
    val mockRide =
        Ride(
            id = 1,
            departureCity = "Ithaca",
            arrivalCity = "Syracuse",
            departureTime = "9:00 AM",
            arrivalTime = "1:00 PM",
            date = "05/02/2025",
            availableSeats = 3,
            price = 20,
            driverId = 1
        )
    val mockDriver = User(
        id =1 ,
        name = "Melissa Velasquez",
        email = "mv477@cornell.edu",
        phone = "508-514-9485",
        grad_year = 2027,
        requests = emptyList(),
        rides = emptyList(),
        password = "1234"
    )
//  This was commented out, to hardcode in the details of a mock ride above for the final video
//    val userId = SessionManger.currentUserId
//    val uiState = detailsViewModel.uiStateFlow.collectAsState().value

//    LaunchedEffect(rideId) {
//        detailsViewModel.loadRideById(rideId)
//    }

//    if (uiState.loading) {
//        Box(
//            modifier = Modifier
//                .fillMaxSize(),
//            contentAlignment = Alignment.Center
//        ){
//            Column(
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Image(
//                    painter = painterResource(R.drawable.bear),
//                    contentDescription = "Loading bear",
//                    modifier = Modifier.size(60.dp)
//                )
//                Spacer(modifier = Modifier.height(8.dp))
//                Text(
//                    text = "Loading...",
//                    style = MaterialTheme.typography.labelLarge
//                )
//            }
//        }
//        return
//    }
//    val ride = uiState.ride
//    val driver = uiState.driver
//
//    if (ride == null || driver == null) {
//        Box(
//            modifier = Modifier.fillMaxSize(),
//            contentAlignment = Alignment.Center
//        ){
//            Text("Ride or driver not found. Try again later.")
//        }
//        return
//    }

    Column(
        modifier = Modifier.background(Color(0xFFFAF1E6))
    ) {
        AppHeader(onClick = { navController.navigate(Screen.LoginScreen) }, Color(0xFFFAF1E6))
        Surface(
            shape = RoundedCornerShape(24.dp),
            shadowElevation = 4.dp,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(24.dp)
            ) {
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    IconButton(
                        onClick= {navController.popBackStack()}
                    ) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back")
                    }
                    Spacer(modifier = Modifier.width(64.dp))
                    Text(
                        text = "Trip Details",
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
                ) {
                    Column(
                        verticalArrangement = Arrangement.Top
                    ) {
                        ProfilePic(mockDriver.name)
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        Text(
                            text = mockDriver.name,
                            style = MaterialTheme.typography.titleMedium,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = mockDriver.grad_year.toString(),
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Contact:",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = "${mockDriver.email}\n${mockDriver.phone}",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
                HorizontalDivider(color = Color.LightGray, thickness = 2.dp)
                Spacer(modifier = Modifier.height(8.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(R.drawable.sidecar),
                        contentDescription = "Bus Icon",
                        modifier = Modifier.size(30.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = "Departing ride • ${mockRide.date}",
                        style = MaterialTheme.typography.displayLarge,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))

                Row(verticalAlignment = Alignment.CenterVertically){
                    Icon(
                        painter = painterResource(R.drawable.ppl),
                        contentDescription = "person",
                        modifier = Modifier.size(30.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "${mockRide.availableSeats}",
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    verticalAlignment = Alignment.Top
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.opencircle),
                            contentDescription = "start",
                            modifier = Modifier.size(16.dp)
                        )
                        Icon(
                            painter = painterResource(R.drawable.dotdot),
                            contentDescription = "path",
                            modifier = Modifier.size(32.dp)
                        )
                        Icon(
                            painter = painterResource(R.drawable.dotdot),
                            contentDescription = "path",
                            modifier = Modifier.size(32.dp)
                        )
                        Icon(
                            painter = painterResource(R.drawable.dotdot),
                            contentDescription = "path",
                            modifier = Modifier.size(32.dp)
                        )
                        Icon(
                            painter = painterResource(R.drawable.dotdot),
                            contentDescription = "path",
                            modifier = Modifier.size(32.dp)
                        )
                        Icon(
                            painter = painterResource(R.drawable.dotdot),
                            contentDescription = "path",
                            modifier = Modifier.size(32.dp)
                        )
                        Icon(
                            painter = painterResource(R.drawable.dotdot),
                            contentDescription = "path",
                            modifier = Modifier.size(32.dp)
                        )
                        Icon(
                            painter = painterResource(R.drawable.opencircle),
                            contentDescription = "end",
                            modifier = Modifier.size(16.dp)
                        )
                    }
                    Column(
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Top
                    ) {
                        Text(
                            text = mockRide.departureTime,
                            style = MaterialTheme.typography.displayLarge,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = mockRide.departureCity,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(modifier = Modifier.height(86.dp))
                        Text(
                            text = mockRide.arrivalTime,
                            style = MaterialTheme.typography.displayLarge,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = mockRide.arrivalCity,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
                Spacer(modifier = Modifier.height(32.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    if (mockRide.price > 0) {
                        Text(
                            text = "$${mockRide.price}",
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Button(
                        onClick = {
                            requestSent = true

                        },
//                        enabled = !uiState.rideRequested,
                        shape = RoundedCornerShape(24.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (requestSent) Color.Gray else Color(0xFFD15429)
                        ),
                        modifier = Modifier.height(50.dp)
                    ) {
                        if (!requestSent){
                            Text(
                                text = "Request"
                            )
                        } else {
                            Text("Requested")
                        }


                    }
//                    also commented out for the final video to not display an error
//                    uiState.errorMessage?.let {
//                        Spacer(modifier = Modifier.height(8.dp))
//                        Text(
//                            text = it,
//                            color = Color.Red,
//                            fontWeight = FontWeight.Bold
//                        )
//                    }
                }


            }
        }
    }
}