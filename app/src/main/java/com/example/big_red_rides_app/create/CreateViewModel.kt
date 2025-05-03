package com.example.big_red_rides_app.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.big_red_rides_app.Screen
import com.example.big_red_rides_app.SessionManger
import com.example.big_red_rides_app.retrofit.RetrofitInstance
import com.example.big_red_rides_app.retrofit.RideCreationRequest
import com.example.big_red_rides_app.retrofit.RideRequest
import com.example.big_red_rides_app.util.UIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateViewModel @Inject constructor(
    private val retrofitInstance: RetrofitInstance
): ViewModel() {
    private val _uiStateFlow = MutableStateFlow(UiState())
    val uiState = _uiStateFlow.asStateFlow()

    data class UiState(
        val from:  String = "",
        val to: String = "",
        val date: String = "",
        val departureTime: String = "",
        val arrivalTime: String = "",
        val seatsAvailable: String = "",
        val price: String = "",
        val loading: Boolean = false,
        val errorMessage: String? = null,
        val navEvent: UIEvent<Screen>? = null
    )

    fun onFromChanged(value: String) {
        _uiStateFlow.value = _uiStateFlow.value.copy(from = value)
    }

    fun onToChanged(value: String) {
        _uiStateFlow.value = _uiStateFlow.value.copy(to = value)
    }

    fun swapLocations() {
        val currentFrom = _uiStateFlow.value.from
        val currentTo = _uiStateFlow.value.to
        _uiStateFlow.value = _uiStateFlow.value.copy(
            from = currentTo,
            to = currentFrom
        )
    }

    fun onDateChanged(value: String) {
        _uiStateFlow.value = _uiStateFlow.value.copy(date = value)
    }

    fun onDepartureTimeChanged(value: String) {
        _uiStateFlow.value = _uiStateFlow.value.copy(departureTime = value)
    }

    fun onArrivalTimeChanged(value: String) {
        _uiStateFlow.value = _uiStateFlow.value.copy(arrivalTime = value)
    }

    fun onSeatsAvailableChanged(value: String) {
        _uiStateFlow.value = _uiStateFlow.value.copy(seatsAvailable = value)
    }

    fun onPriceChanged(value: String) {
        _uiStateFlow.value = _uiStateFlow.value.copy(price = value)
    }

    fun onCreateCLicked() {
        val state = _uiStateFlow.value
        val driverId = SessionManger.currentUserId
        if (driverId == null) {
            _uiStateFlow.value = state.copy(
                errorMessage = "you must be logged in to create a ride"
            )
            return
        }

        val rideRequest = RideCreationRequest(
            departure_city = state.from,
            arrival_city =  state.to,
            departure_time = state.departureTime,
            arrival_time = state.arrivalTime,
            date = state.date,
            available_seats = state.seatsAvailable.toIntOrNull() ?: 4,
            price = state.price.toIntOrNull() ?: 0,
            driver_id = driverId
        )

        _uiStateFlow.value = state.copy(
            loading = true,
            errorMessage = null
        )

        viewModelScope.launch {
            try {
                val response = retrofitInstance.rideApiService.createRide(rideRequest)
                if (response.isSuccessful) {
                    _uiStateFlow.value = _uiStateFlow.value.copy(
                        navEvent = UIEvent(Screen.RidesScreen),
                        loading = false
                    )
                } else {
                    _uiStateFlow.value = _uiStateFlow.value.copy(
                        errorMessage = "Failed to create ride"
                    )
                }
            } catch (e: Exception) {
                _uiStateFlow.value = _uiStateFlow.value.copy(
                    errorMessage = "Network error",
                    loading = false
                )
            }
        }
    }


}