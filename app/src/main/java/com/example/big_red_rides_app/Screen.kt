package com.example.big_red_rides_app

import androidx.navigation.NavBackStackEntry
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable


@Serializable
sealed class Screen {
    @Serializable
    data object FindScreen : Screen()

    @Serializable
    data object CreateScreen : Screen()

    @Serializable
    data object RidesScreen : Screen()

    @Serializable
    data object SignUpScreen : Screen()

    @Serializable
    data object LoginScreen : Screen()

    @Serializable
    data class DetailsScreen(val rideId: Int) : Screen()

    fun NavBackStackEntry.toScreen(): Screen? =
        when (destination.route?.substringAfterLast(".")?.substringBefore("/")) {
            "FindScreen" -> toRoute<FindScreen>()
            "CreateScreen" -> toRoute<CreateScreen>()
            "RidesScreen" -> toRoute<RidesScreen>()
            "LoginScreen" -> toRoute<LoginScreen>()
            "SignUpScreen" -> toRoute<SignUpScreen>()
            "DetailsScreen" -> toRoute<DetailsScreen>()
            else -> null
        }
}