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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import com.example.big_red_rides_app.SessionManger
import com.example.big_red_rides_app.composables.AppHeader
import com.example.big_red_rides_app.composables.DriverRequestCard
import com.example.big_red_rides_app.composables.PassengerRequestCard
import com.example.big_red_rides_app.composables.PersonalGreeting
import com.example.big_red_rides_app.retrofit.Ride
import com.example.big_red_rides_app.retrofit.RideDisplay
import com.example.big_red_rides_app.retrofit.RideRequest
import com.example.big_red_rides_app.retrofit.User

@Composable
fun RidesScreen(
    currentUser: User,
    navController: NavController,
    viewModel: RidesViewModel = hiltViewModel()
){
    val uiState = viewModel.uiStateFlow.collectAsState().value

    LaunchedEffect(Unit) {
        SessionManger.currentUserId?.let {
            viewModel.loadData(it)
        }
    }

    var isDriverView by remember { mutableStateOf(true) }

    Column {
        AppHeader(onClick = {navController.navigate(Screen.LoginScreen)}, color = Color.White)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
          Column(
              modifier = Modifier
                  .weight(1f)
                  .clickable { isDriverView = false},
          ) {
              Row(
                  verticalAlignment = Alignment.CenterVertically
              ) {
                  Icon(
                      painter = painterResource(R.drawable.passenger),
                      contentDescription = "passenger  view",
                      tint = if (!isDriverView) Color(0xFFD15429) else Color.Black,
                      modifier = Modifier.size(20.dp)
                  )
                  Spacer(modifier = Modifier.width(4.dp))
                  Text(
                      text = "Passenger",
                      style = MaterialTheme.typography.titleMedium,
                      fontSize = 25.sp,
                      fontWeight = FontWeight.Bold,
                      color = if (!isDriverView) Color(0xFFD15429) else Color.Black
                  )
              }
              HorizontalDivider(
                  color = if (!isDriverView) Color(0xFFD15429) else Color.White,
                  thickness = 2.dp
              )
          }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .clickable { isDriverView = true},
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.car),
                        contentDescription = "driver  view",
                        tint = if (isDriverView) Color(0xFFD15429)else Color.Black,
                        modifier = Modifier.size(25.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Driver",
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (isDriverView) Color(0xFFD15429) else Color.Black
                    )
                }
                HorizontalDivider(
                    color = if (isDriverView) Color(0xFFD15429) else Color.White,
                    thickness = 2.dp
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        if (isDriverView) {
            PersonalGreeting(currentUser, isDriverView)

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                item {
                    var expand by remember { mutableStateOf(true) }

                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { expand = !expand }
                                .padding(vertical = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "05/02/2025 Trip to Syracuse",
                                style = MaterialTheme.typography.titleMedium,
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Bold,
                            )
                            Icon(
                                imageVector = if (expand) Icons.Default.KeyboardArrowDown
                                else Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                                contentDescription = "drop down"
                            )
                        }

                        HorizontalDivider(color = Color.Black, modifier = Modifier.height(2.dp))
                        Spacer(modifier = Modifier.height(4.dp))

                        if (expand) { //had to use mock data for the sake of the video
                            val mockRequest = RideRequest(
                                id = 101,
                                rideId = 3,
                                passengerId = 1,
                                status = "pending"
                            )
                            val mockPassenger = User(
                                id = 1,
                                name = "Melissa Velasquez",
                                email = "mv477@cornell.edu",
                                phone = "508-514-9485",
                                grad_year = 2027,
                                password = "1234",
                                requests = emptyList(),
                                rides = emptyList()
                            )

                            DriverRequestCard(
                                requestDisplay = RideDisplay(mockRequest, mockPassenger),
                                onAcceptClicked = { }
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            HorizontalDivider()
                            Spacer(modifier = Modifier.height(4.dp))
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }


    } else {
                val myRequests = uiState.rideRequests.filter { it.passengerId == currentUser.id }

                PersonalGreeting(currentUser, (isDriverView))
                Spacer(modifier = Modifier.height(16.dp))

                Surface(
                    shape = RoundedCornerShape(24.dp),
                    shadowElevation = 4.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    color = Color(0xFFFAF1E6)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Ride Requests:",
                            style = MaterialTheme.typography.titleMedium,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.ExtraBold,
                        )
                        Spacer(modifier = Modifier.height(32.dp))
                        LazyColumn {
                            items(myRequests.size) { index ->
                                val request = myRequests[index]
                                val headerRide = uiState.rides.find { it.id == request.rideId }
                                var expand by remember { mutableStateOf(false) }

                                Column(
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clickable { expand = !expand }
                                            .padding(vertical = 8.dp),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = if (headerRide != null) "${headerRide.date} Trip to ${headerRide.arrivalCity}" else "Ride not found.",
                                            style = MaterialTheme.typography.titleMedium,
                                            fontSize = 25.sp,
                                            fontWeight = FontWeight.Bold,
                                        )
                                        Icon(
                                            imageVector = if (expand) {
                                                Icons.Default.KeyboardArrowDown
                                            }else {
                                                Icons.AutoMirrored.Filled.KeyboardArrowLeft
                                            },
                                            contentDescription = "drop down"
                                        )
                                    }
                                    HorizontalDivider(color = Color.Black)
                                    if (expand) {
                                        if (myRequests.isEmpty()){
                                            Text(
                                                text = "No rides requested yet",
                                                style = MaterialTheme.typography.titleMedium
                                            )
                                        } else {
                                            myRequests.forEach{ request ->
                                                val ride = uiState.rides.find { it.id == request.rideId }
                                                val driver = uiState.users.find { it.id == ride?.driverId }
                                                PassengerRequestCard(request, ride, driver, { viewModel.cancelRequest(request.id)})
                                                Spacer(modifier = Modifier.height(6.dp))

                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

            }
        }


}