package com.example.big_red_rides_app.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.big_red_rides_app.retrofit.RequestCreateDTO
import com.example.big_red_rides_app.retrofit.RetrofitInstance
import com.example.big_red_rides_app.retrofit.Ride
import com.example.big_red_rides_app.retrofit.User

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.sql.Driver
import javax.inject.Inject


@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val retrofitInstance: RetrofitInstance
): ViewModel() {

    private val _uiStateFlow = MutableStateFlow(UiState())
    val uiStateFlow = _uiStateFlow.asStateFlow()

    data class UiState(
        val loading: Boolean = true,
        val ride: Ride? = null,
        val driver: User? = null,
        val rideRequested : Boolean = false,
        val errorMessage: String? = null
    )


    //these functions were not used for the final video, cause we were unable to load real rides on the find screen
    fun loadRideById(rideId: Int) {
        viewModelScope.launch {
            _uiStateFlow.value = _uiStateFlow.value.copy(loading = true)
            try {
                val ride = retrofitInstance.rideApiService.getRideById(rideId)
                val driver = retrofitInstance.userApiService.getUserById(ride.driverId)
                _uiStateFlow.value = _uiStateFlow.value.copy(
                    loading = false,
                    driver = driver,
                    ride = ride
                )
            } catch (e: Exception) {
                _uiStateFlow.value = _uiStateFlow.value.copy(
                    loading = false,
                    errorMessage = "Failed to load driver or driver"
                )
            }
        }
    }

    fun requestRide(passengerId: Int) {
        val rideId = uiStateFlow.value.ride?.id ?: return
        viewModelScope.launch {
            try{
                retrofitInstance.requestApiService.createRequest(
                    RequestCreateDTO(
                        ride_Id = rideId,
                        passenger_Id = passengerId
                    )
                )
            } catch (e: Exception){
                _uiStateFlow.value = _uiStateFlow.value.copy(
                    errorMessage = "Failed to request ride. please try again."
                )
            }
        }
    }

}