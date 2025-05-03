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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.big_red_rides_app.R
import com.example.big_red_rides_app.Screen
import com.example.big_red_rides_app.composables.AppHeader
import com.example.big_red_rides_app.composables.LabeledTextField
import com.example.big_red_rides_app.composables.RideCard
import kotlinx.serialization.json.JsonNull.content

@Composable
fun FindScreen(
    navController: NavController,
    viewModel: FindViewModel = hiltViewModel()
){
    val uiState = viewModel.uiStateFlow.collectAsState().value


    LaunchedEffect(uiState.navEvent) {
        uiState.navEvent?.consume { route ->
            navController.navigate(route)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(12.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppHeader(onClick = { navController.navigate(Screen.LoginScreen) }, Color.White)

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                }
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        LabeledTextField(
                            icon = R.drawable.opencircle,
                            label = "From*",
                            description = "Enter town of city",
                            value = uiState.from,
                            onValueChange = { viewModel.onFromChanged(it)}
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        LabeledTextField(
                            icon = R.drawable.locationpin,
                            label = "To*",
                            description = "Enter town of city",
                            value = uiState.to,
                            onValueChange = { viewModel.onToChanged(it)}
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    IconButton(
                        onClick = { viewModel.swapLocations()},
                        modifier = Modifier.align((Alignment.CenterVertically))
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.swaploc),
                            contentDescription = "Swap",
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    LabeledTextField(
                        icon = R.drawable.calendar,
                        label = "Departure*",
                        description = "mm/dd/yyyy",
                        value = uiState.date,
                        onValueChange = { viewModel.onDateChanged(it)},
                        modifier = Modifier.weight(1f)

                    )


                    Spacer(modifier = Modifier.width(8.dp))

                    Button(
                        onClick = { viewModel.onSearch() },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD15429)),
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
        Spacer(modifier = Modifier.height(12.dp))

        uiState.errorMessage?.let {
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = it,
                color = Color.Red,
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.titleMedium
            )
        }
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
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    shadowElevation = 4.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    color = Color(0xFFFAF1E6)
                ){
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ){
                        Text(text = "No Results Found",
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold
                        )
                    }
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
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xFFFAF1E6))
                            .padding(4.dp)
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
                                color = Color(0xFFD15429)
                            )
                        }

//                        Spacer(modifier = Modifier.height(4.dp))

                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(uiState.rideResults) { ride ->
                                val driver = uiState.users.find { it.id == ride.driverId}
                                RideCard(
                                    ride = ride,
                                    driver = driver,
                                    onClick = { viewModel.onRideCardClicked(ride.id)} )
                            }
                        }
                    }
                }
            }

        }
            }
        }



