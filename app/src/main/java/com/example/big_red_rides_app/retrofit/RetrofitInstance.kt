package com.example.big_red_rides_app.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitInstance @Inject constructor(){

    private val okHttpClient = OkHttpClient.Builder().build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://35.212.100.214/api/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val userApiService: UserApiService = retrofit.create(UserApiService::class.java)


    val rideApiService: RideApiService = retrofit.create(RideApiService::class.java)

    val requestApiService: RequestApiService = retrofit.create(RequestApiService::class.java)

}