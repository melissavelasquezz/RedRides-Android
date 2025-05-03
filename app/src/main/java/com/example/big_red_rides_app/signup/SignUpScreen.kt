package com.example.big_red_rides_app.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.big_red_rides_app.R

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel(),
    navController: NavController
){
    val uiState = viewModel.uiStateFlow.collectAsState().value

    LaunchedEffect(uiState.navEvent) {
        uiState.navEvent?.consume { screen ->
            navController.navigate(screen)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ){
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(R.drawable.bear),
                contentDescription = "logo",
                modifier = Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.height(18.dp))

            Row {
                Text(
                    text = "Red",
                    style = TextStyle(
                        fontSize = 30.sp,
                        fontWeight = FontWeight(700),
                        color = Color(0xFFD15429),
                    )
                )
                Text(
                    text = "Rides",
                    style = TextStyle(
                        fontSize = 30.sp,
                        fontWeight = FontWeight(700),
                        color = Color.Black,
                    )
                )
            }
        }
        Text(
            text = "Get started with your account",
            style = MaterialTheme.typography.titleMedium,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(24.dp))

        var name by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var year by remember { mutableStateOf("") }
        var phone by remember { mutableStateOf("") }

        OutlinedTextField(
            value = name,
            onValueChange = {name = it},
            label = { Text(text = "Name", fontWeight = FontWeight.Bold)},
            placeholder = { Text("First and last") },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFD15429)
            )

        )
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            value = email,
            onValueChange = {email = it},
            label = { Text(text = "Email", fontWeight = FontWeight.Bold)},
            placeholder = { Text("netid@cornell.edu") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFD15429)
            )

        )
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            value = password,
            onValueChange = {password = it},
            label = { Text(text ="Password", fontWeight = FontWeight.Bold)},
            placeholder = { Text("Required") },
//            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFD15429)
            )

        )
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            value = year,
            onValueChange = {year = it},
            label = { Text(text = "Year", fontWeight = FontWeight.Bold)},
            placeholder = { Text("202X") },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFD15429)
            )

        )
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            value = phone,
            onValueChange = {phone = it},
            label = { Text(text ="Phone", fontWeight = FontWeight.Bold)},
            placeholder = { Text("123-456-7890") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFD15429)
            )

        )
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { viewModel.OnCreateAccountClicked(name, email, password, year, phone) },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD15429)),
            modifier = Modifier
                .width(160.dp)
                .height(40.dp)
        ) {
            Text(
                text = "Sign Up",
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }

        uiState.errorMessage?.let {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = it,
                color = Color.Red,
                fontWeight = FontWeight.SemiBold
            )
        }

    }
}
