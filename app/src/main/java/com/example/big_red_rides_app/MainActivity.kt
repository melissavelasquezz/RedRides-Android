package com.example.big_red_rides_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.navigation.compose.NavHost
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.composable
import com.example.big_red_rides_app.create.CreateScreen
import com.example.big_red_rides_app.rides.RidesScreen
import com.example.big_red_rides_app.find.FindScreen
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.big_red_rides_app.Screen.FindScreen.toScreen
import com.example.big_red_rides_app.details.DetailScreen
import com.example.big_red_rides_app.details.DetailsViewModel
import com.example.big_red_rides_app.rides.RequestStatus
import com.example.big_red_rides_app.rides.mockRideRequests
import com.example.big_red_rides_app.rides.mockRides
import com.example.big_red_rides_app.ui.theme.Big_Red_Rides_AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Big_Red_Rides_AppTheme {
                val navController = rememberNavController()
                val navBackStackEntry = navController.currentBackStackEntryAsState().value

                Scaffold(modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        NavigationBar {
                            tabs.map { item ->
                                NavigationBarItem(
                                    selected = item.screen == navBackStackEntry?.toScreen(),
                                    onClick = { navController.navigate(item.screen) },
                                    icon = { Icon(imageVector = item.icon, contentDescription = null) },
                                    label = { Text(text = item.label)}
                                )
                            }
                        }
                    }
                    ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        NavHost(
                            navController = navController,
                            startDestination = Screen.FindScreen
                        ){
                            composable<Screen.FindScreen>{
                                FindScreen(navController)
                            }
                            composable<Screen.CreateScreen>{
                                CreateScreen(navController)
                            }
                            composable<Screen.RidesScreen>{
                                RidesScreen(
                                    driverRides = mockRides.filter { it.driverId == 1  }, //currentuser?
                                    rideRequests = mockRideRequests,
                                    onAcceptClicked = { request ->
                                        request.status = RequestStatus.ACCEPTED
                                    }
                                )
                            }
                            composable(
                                route = "details/{rideId}"
                            ){ backStackEntry ->
                                val rideId = backStackEntry.arguments?.getString("rideId")
                                DetailScreen(
                                    navController = navController
                                )

                            }
                        }
                    }
                }
            }
        }
    }
}

data class NavItem(
    val screen : Screen,
    val label: String,
    val icon: ImageVector
)

val tabs = listOf(
    NavItem(
        label = "Find",
        icon = Icons.Filled.Search,
        screen = Screen.FindScreen,
    ),
    NavItem(
        label = "Create",
        icon = Icons.Filled.Add,
        screen = Screen.CreateScreen,
    ),
    NavItem(
        label = "My Rides",
        icon = Icons.Filled.ShoppingCart,
        screen = Screen.RidesScreen,
    )
)