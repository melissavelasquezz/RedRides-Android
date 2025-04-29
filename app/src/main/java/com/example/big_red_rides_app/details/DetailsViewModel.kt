package com.example.big_red_rides_app.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.big_red_rides_app.retrofit.RetrofitInstance
import com.example.big_red_rides_app.rides.Ride
import com.example.big_red_rides_app.rides.mockRides
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _uiStateFlow = MutableStateFlow(UiState())
    val uiStateFlow = _uiStateFlow.asStateFlow()

    data class UiState(
        val loading: Boolean = true,
        val ride: Ride? = null,
        val rideRequested : Boolean = false
    )

    private val rideId = savedStateHandle.get<String>("rideId")?.toIntOrNull()

    fun loadRideData() {
        viewModelScope.launch {
            _uiStateFlow.value = _uiStateFlow.value.copy(loading = true)
            delay(500)
            val ride = mockRides.find { it.id == rideId}
            _uiStateFlow.value = _uiStateFlow.value.copy(
                loading = false,
                ride = ride
            )
        }
    }

    fun requestRide() {
        _uiStateFlow.value = _uiStateFlow.value.copy(
            rideRequested = true
        )
    }

    init {
        loadRideData()
    }
}