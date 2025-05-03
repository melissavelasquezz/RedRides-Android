package com.example.big_red_rides_app.retrofit

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface RideApiService {
    @GET("rides/")
    suspend fun getAllRides(): List<Ride>

    @GET("rides/{id}/")
    suspend fun getRideById(@Path("id") id: Int): Ride

    @POST("rides/")
    suspend fun createRide(@Body ride: RideCreationRequest): Response<Ride>
}

data class Ride(
    val id: Int,
    val departureCity: String,
    val arrivalCity: String,
    val departureTime: String,
    val arrivalTime: String,
    val date: String,
    val availableSeats: Int,
    val price: Int,
    val driverId: Int,
)

data class  RideDisplay(
    val request: RideRequest,
    val passenger: User
)

data class RideCreationRequest(
    val departure_city: String,
    val arrival_city: String,
    val departure_time: String,
    val arrival_time: String,
    val date: String,
    val available_seats: Int,
    val price: Int,
    val driver_id: Int
)