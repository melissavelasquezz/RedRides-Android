package com.example.big_red_rides_app.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.navigation.NavController

@Composable
fun LoginScreen(navController: NavController){
    Column(
        modifier = Modifier.fillMaxSize(),

    ) {
        Text(
            text = "login page"
        )
    }
}