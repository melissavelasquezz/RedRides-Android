package com.example.big_red_rides_app.create

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.big_red_rides_app.R
import com.example.big_red_rides_app.Screen
import com.example.big_red_rides_app.composables.AppHeader
import com.example.big_red_rides_app.composables.LabeledTextField

@Composable
fun CreateScreen(
   navController: NavController,
   createViewModel: CreateViewModel = hiltViewModel()
){
   val uiState = createViewModel.uiState.collectAsState().value

   LaunchedEffect(uiState.navEvent) {
      uiState.navEvent?.consume { screen ->
         navController.navigate(screen)
      }
   }

   if(uiState.loading){
      Text(
         text = "Creating ride..",
      )
   }
   Column(
      modifier = Modifier
         .fillMaxSize()
         .background(Color(0xFFFAF1E6)),
      verticalArrangement = Arrangement.Top,
      horizontalAlignment = Alignment.CenterHorizontally
   ) {
      AppHeader(onClick = { navController.navigate(Screen.LoginScreen) }, Color(0xFFFAF1E6))

      Surface(
         shape = RoundedCornerShape(16.dp),
         shadowElevation = 4.dp,
         modifier = Modifier
            .fillMaxWidth()
      ) {
         Column(
            modifier = Modifier
               .fillMaxWidth()
               .verticalScroll(rememberScrollState())
               .padding(horizontal = 24.dp, vertical = 16.dp)
               .padding(bottom = 80.dp),
         ){
            Column(
               modifier = Modifier
                  .fillMaxWidth()
            ) {
               Row(
                  verticalAlignment = Alignment.CenterVertically
               ) {
                  IconButton(
                     onClick = { navController.popBackStack()}
                  ) {
                     Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back to rides"
                     )
                  }
                  Spacer(modifier = Modifier.width(16.dp))
                  Text(
                     text ="New Trip",
                     style = MaterialTheme.typography.headlineSmall,
                     fontWeight = FontWeight.ExtraBold
                  )
               }
               Spacer(modifier = Modifier.height(8.dp))
               Text(
                  text = "Enter trip information:",
                  style = MaterialTheme.typography.titleMedium,
                  fontWeight = FontWeight.Bold
               )
               Spacer(modifier = Modifier.height(12.dp))
               LabeledTextField(
                  icon = R.drawable.opencircle,
                  label = "From*",
                  description = "Enter town of city",
                  value = uiState.from,
                  onValueChange = { createViewModel.onFromChanged(it)}
               )

               Spacer(modifier = Modifier.height(12.dp))
            }
            Row(
               modifier = Modifier.fillMaxWidth()
            ) {
               Column(
                  modifier = Modifier.weight(1f)
               ) {
                  LabeledTextField(
                     icon = R.drawable.locationpin,
                     label = "To*",
                     description = "Enter town of city",
                     value = uiState.to,
                     onValueChange = { createViewModel.onToChanged(it)}
                  )
               }
               Spacer(modifier = Modifier.width(8.dp))
               IconButton(
                  onClick = {createViewModel.swapLocations()},
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

            LabeledTextField(
               icon = R.drawable.calendar,
               label = "Departure*",
               description = "mm/dd/yyyy",
               value = uiState.date,
               onValueChange = { createViewModel.onDateChanged(it)}
            )
            Spacer(modifier = Modifier.height(16.dp))

            Row(
               modifier = Modifier.fillMaxWidth()
            ) {
               LabeledTextField(
                  icon = R.drawable.clock,
                  label = "Departure time*",
                  description = "e.g. 9:00 AM",
                  value = uiState.departureTime,
                  onValueChange = { createViewModel.onDepartureTimeChanged(it)},
                  modifier = Modifier.weight(1f)
               )
               Spacer(modifier = Modifier.width(16.dp))
               LabeledTextField(
                  icon = R.drawable.clock,
                  label = "Arrival time*",
                  description = "e.g. 1:00 PM",
                  value = uiState.arrivalTime,
                  onValueChange = { createViewModel.onArrivalTimeChanged(it)},
                  modifier = Modifier.weight(1f)
               )
            }
            Spacer(modifier = Modifier.height(16.dp))
            LabeledTextField(
               icon = R.drawable.ppl,
               label = "Seats Available*",
               description = "# people",
               value = uiState.seatsAvailable,
               onValueChange = { createViewModel.onSeatsAvailableChanged(it)},
            )
            Spacer(modifier = Modifier.height(16.dp))
            LabeledTextField(
               icon = R.drawable.money,
               label = "Ride Fee*",
               description = "$ 0",
               value = uiState.price,
               onValueChange = { createViewModel.onPriceChanged(it)},
            )
            Spacer(modifier = Modifier.height(32.dp))

            Row(
               modifier = Modifier.fillMaxWidth(),
               horizontalArrangement = Arrangement.End
            ) {
               Button(
                  onClick = { createViewModel.onCreateCLicked() },
                  colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD15429)))
               {
                  Text(
                     text = "Create"
                  )
               }
            }
            uiState.errorMessage?.let{
               Text(
                  text = it
               )
            }
      }
      }
   }

}