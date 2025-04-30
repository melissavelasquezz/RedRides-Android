package com.example.big_red_rides_app.find

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.big_red_rides_app.R
import com.example.big_red_rides_app.Screen
import com.example.big_red_rides_app.composables.AppHeader
import com.example.big_red_rides_app.composables.RideCard
import kotlinx.serialization.json.JsonNull.content

@Composable
fun FindScreen(
    navController: NavController,
    findViewModel: FindViewModel = hiltViewModel()
){
    val uiState = findViewModel.uiStateFlow.collectAsState().value

//    LaunchedEffect(uiState.navEvent) {
//        uiState.navEvent?.consume { route ->
//            navController.navigate(route)
//        }
//    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        AppHeader(onClick = {navController.navigate(Screen.LoginScreen)})
        Column (modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 4.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ){
                Column(
                    modifier = Modifier.weight(1f)
                ){
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Icon(
                            painter = painterResource(R.drawable.opencircle),
                            contentDescription = "from icon",
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "From",
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.height(2.dp))
                    OutlinedTextField(
                        value = uiState.from,
                        onValueChange = { findViewModel.onFromChanged(it)},
                        label = {
                            Text(
                                text = "Enter a town or city",
                                style = MaterialTheme.typography.labelLarge
                            )},
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = Color.White,
                            unfocusedBorderColor = Color.Black,
                            focusedBorderColor = Color.Black,
                            cursorColor = Color.Black,
                            unfocusedLabelColor = Color.DarkGray
                        )
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Icon(
                            painter = painterResource(R.drawable.locationpin),
                            contentDescription = "to icon",
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "To",
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.height(2.dp))
                    OutlinedTextField(
                        value = uiState.to,
                        onValueChange = { findViewModel.onToChanged(it)},
                        label = {
                            Text(
                                text = "Enter a town or city",
                                style = MaterialTheme.typography.labelLarge
                            )},
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = Color.White,
                            unfocusedBorderColor = Color.Black,
                            focusedBorderColor = Color.Black,
                            cursorColor = Color.Black,
                            unfocusedLabelColor = Color.DarkGray
                        )
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                }
                Spacer(modifier = Modifier.width(12.dp))

                IconButton(
                    onClick = { findViewModel.swapLocations() },
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.swaploc),
                        contentDescription = "swap to and from",
                        modifier = Modifier.size(30.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    painter = painterResource(R.drawable.calendar),
                    contentDescription = "calendar icon",
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Departure",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                OutlinedTextField(
                    value = uiState.date,
                    onValueChange = { findViewModel.onDateChanged(it)},
                    label = {
                        Text(
                            text = "mm/dd/yyyy",
                            style = MaterialTheme.typography.labelLarge
                        )},
                    singleLine = true,
                    modifier = Modifier.weight(1f),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedContainerColor = Color.White,
                        unfocusedBorderColor = Color.Black,
                        focusedBorderColor = Color.Black,
                        cursorColor = Color.Black,
                        unfocusedLabelColor = Color.DarkGray
                    )
                )

                Spacer(modifier = Modifier.width(12.dp))

                Button(
                    onClick = { findViewModel.onSearch() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                    modifier = Modifier.height(50.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "search"
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Search",
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        if (uiState.loading){
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(R.drawable.bear),
                        contentDescription = "Loading bear",
                        modifier = Modifier.size(60.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Loading...",
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }
        } else {
            if (uiState.rideResults.isEmpty()){
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ){
                    Text(text = "No Results",
                        style = MaterialTheme.typography.labelLarge
                        )
                }
            } else {
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    shadowElevation = 4.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize().background(Color(0xFFFAF1E6)).padding(4.dp)
                    ) {
                        Row {
                            Text(
                                text = "    ${uiState.rideResults.size} results from ${uiState.from} to ",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(bottom = 4.dp)
                            )
                            Text(
                                text = "${uiState.to}:",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(bottom = 4.dp),
                                color = Color.Red
                            )
                        }

//                        Spacer(modifier = Modifier.height(4.dp))

                        LazyColumn(
                            modifier = Modifier.fillMaxSize().padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(uiState.rideResults) { ride ->
                                RideCard(
                                    ride = ride,
                                    onClick = {navController.navigate("details/${ride.id}")} )
                            }
                        }
                    }
                }
            }

        }

    }

}