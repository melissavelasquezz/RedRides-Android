package com.example.big_red_rides_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.navigation.compose.NavHost
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.composable
import com.example.big_red_rides_app.create.CreateScreen
import com.example.big_red_rides_app.rides.RidesScreen
import com.example.big_red_rides_app.find.FindScreen
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.big_red_rides_app.Screen.FindScreen.toScreen
import com.example.big_red_rides_app.details.DetailScreen
import com.example.big_red_rides_app.retrofit.User
import com.example.big_red_rides_app.signup.LoginScreen
import com.example.big_red_rides_app.signup.SignUpScreen
import com.example.big_red_rides_app.signup.SignUpViewModel
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
                        val currentScreen = navBackStackEntry?.toScreen()
                        if (currentScreen in listOf(
                                Screen.FindScreen,
                                Screen.CreateScreen,
                                Screen.RidesScreen
                            )) {
                            NavigationBar {
                                tabs.map { item ->
                                    NavigationBarItem(
                                        selected = item.screen == navBackStackEntry?.toScreen(),
                                        onClick = { navController.navigate(item.screen) },
                                        icon = {
                                            Icon(
                                                painter = painterResource(item.icon),
                                                contentDescription = null,
                                                modifier = Modifier.size(25.dp)
                                            )
                                        },
                                        label = {
                                            Text(
                                                text = item.label,
                                                style = MaterialTheme.typography.titleMedium,
                                                fontWeight = FontWeight.Bold
                                            )
                                        },
                                        colors = NavigationBarItemDefaults.colors(
                                            indicatorColor = Color(0xFFD15429),
                                        )
                                    )
                                }
                            }
                        }
                    }
                    ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        NavHost(
                            navController = navController,
                            startDestination = Screen.LoginScreen
                        ){
                            composable<Screen.FindScreen>{
                                FindScreen(navController)
                            }
                            composable<Screen.CreateScreen>{
                                CreateScreen(navController)
                            }
                            composable<Screen.RidesScreen>{
//                                // commented out to be able to have ride requests appear
//                                val userId = SessionManger.currentUserId
//                                if (userId != null){
//                                    RidesScreen(
//                                        currentUser = SessionManger.currentUser!!,
//                                        navController = navController
//                                    )
//                                }
                                val mockPassenger = User(
                                    id = 3,
                                    name = "Mark Smith",
                                    email = "ms131@cornell.edu",
                                    phone = "0987654321",
                                    grad_year = 2025,
                                    requests = emptyList(),
                                    rides = emptyList(),
                                    password = "1234"
                                )

                                RidesScreen(currentUser = mockPassenger, navController = navController)
                            }
                            composable<Screen.LoginScreen> {
                                LoginScreen(hiltViewModel(), navController)
                            }
                            composable<Screen.SignUpScreen> {
                                SignUpScreen(hiltViewModel(), navController)
                            }
                            composable<Screen.DetailsScreen> { backStackEntry ->
//                                val screen = backStackEntry.toRoute<Screen.DetailsScreen>()
                                DetailScreen(navController, hiltViewModel() )
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
    val icon: Int
)

val tabs = listOf(
    NavItem(
        label = "Find",
        icon = R.drawable.search,
        screen = Screen.FindScreen,
    ),
    NavItem(
        label = "Create",
        icon = R.drawable.add,
        screen = Screen.CreateScreen,
    ),
    NavItem(
        label = "Trips",
        icon = R.drawable.sidecar,
        screen = Screen.RidesScreen,
    )
)