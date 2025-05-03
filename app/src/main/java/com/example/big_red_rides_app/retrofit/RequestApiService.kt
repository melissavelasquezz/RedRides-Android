package com.example.big_red_rides_app.retrofit

import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RequestApiService {

    @POST("requests/")
    suspend fun createRequest(@Body request: RequestCreateDTO)

    @GET("requests/")
    suspend fun getRequests(): List<RideRequest>

    @DELETE("requests/{id}/")
    suspend fun deleteRequest(@Path("id") requestId: Int)

    @POST("request/{id}/")
    suspend fun resolveRequest(
        @Path("id") requestId: Int,
        @Body body: Map<String, String>
    )
}

data class RequestCreateDTO(
    val ride_Id: Int,
    val passenger_Id: Int
)
data class RideRequest(
    val id : Int,
    val rideId: Int,
    val passengerId: Int,
    var status: String,
)

