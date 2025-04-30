package com.example.big_red_rides_app.create

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.big_red_rides_app.Screen
import com.example.big_red_rides_app.composables.AppHeader

@Composable
fun CreateScreen(
   navController: NavController,
   createViewModel: CreateViewModel = hiltViewModel()
){
   val uiState = createViewModel.uiState.collectAsState().value

   if(uiState.createSuccess) {
      LaunchedEffect(Unit) {
         navController.popBackStack()
      }
   }

   Column(
      modifier = Modifier
         .fillMaxSize()
         .padding(24.dp),
      verticalArrangement = Arrangement.Top
   ) {
      AppHeader(onClick = {navController.navigate(Screen.LoginScreen)})
      Row(
         verticalAlignment = Alignment.CenterVertically
      ) {
        IconButton(
           onClick = { navController.popBackStack() }
        ) {
           Icon(
              imageVector = Icons.AutoMirrored.Filled.ArrowBack,
              contentDescription = "Back to rides"
           )
        }
         Spacer(modifier = Modifier.width(8.dp))
         Text(
            text = "New Trip",
            style = MaterialTheme.typography.headlineMedium
         )
      }
      Spacer(modifier = Modifier.height(24.dp))

      Text(
         text = "Enter trip information:",
         style = MaterialTheme.typography.titleMedium
      )
      Spacer(modifier = Modifier.height(24.dp))

      OutlinedTextField(
         value = uiState.from,
         onValueChange = { createViewModel.onFromChanged(it)},
         label = { Text("From*")},
         modifier = Modifier.fillMaxWidth()
      )

      Spacer(modifier = Modifier.height(16.dp))

      OutlinedTextField(
         value = uiState.to,
         onValueChange = { createViewModel.onToChanged(it)},
         label = { Text("Where to?*")},
         modifier = Modifier.fillMaxWidth()
      )

      Spacer(modifier = Modifier.height(16.dp))

      OutlinedTextField(
         value = uiState.date,
         onValueChange = { createViewModel.onDateChanged(it)},
         label = { Text("Departure Date*")},
         modifier = Modifier.fillMaxWidth()
      )

      Spacer(modifier = Modifier.height(16.dp))

      Row(
         modifier = Modifier.fillMaxWidth(),
         horizontalArrangement = Arrangement.SpaceBetween
      ) {
         OutlinedTextField(
            value = uiState.departureTime,
            onValueChange = { createViewModel.onDepartureTimeChanged(it)},
            label = { Text("Departure Time*")},
            modifier = Modifier.weight(1f)
         )
         Spacer(modifier = Modifier.width(16.dp))

         OutlinedTextField(
            value = uiState.arrivalTime,
            onValueChange = { createViewModel.onArrivalTimeChanged(it)},
            label = { Text("Arrival Time*")},
            modifier = Modifier.weight(1f)
         )
      }

      Spacer(modifier = Modifier.height(16.dp))

      OutlinedTextField(
         value = uiState.rideFee,
         onValueChange = { createViewModel.onRideFeeChanged(it)},
         label = { Text("Ride Fee*")},
         modifier = Modifier.fillMaxWidth()
      )

      Spacer(modifier = Modifier.height(32.dp))

      Row(
         modifier = Modifier.fillMaxWidth(),
         horizontalArrangement = Arrangement.SpaceBetween
      ) {
         TextButton(
            onClick = { navController.popBackStack() }
         ) {
            Text("Back")
         }

         Button(
            onClick = { createViewModel.onCreateCLicked() },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE64A19))
         ) {
            Text(text = "Create")
         }
      }


   }

}