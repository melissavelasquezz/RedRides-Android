package com.example.big_red_rides_app.retrofit

import retrofit2.Response

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserApiService {
    @GET("users/")
    suspend fun getAllUsers(): List<User>

    @POST("users/")
    suspend fun createUser(@Body user: UserRequest): Response<User>

    @GET("users/{id}/")
    suspend fun getUserById(@Path("id") id: Int): User

    @POST("users/login/")
    suspend fun loginUser (@Body credentials: LoginRequest): User
}
data class User(
    val id: Int,
    val name: String,
    val email: String,
    val phone: String,
    val password: String,
    val requests: List<RideRequest> ,
    val rides: List<Ride>? = null,
    val grad_year: Int,

    )
data class UserRequest(
    val name: String,
    val email: String,
    val phone: String,
    val password: String,
    val grad_year: Int
)

data class LoginRequest(
    val email: String,
    val password: String
)



