package com.example.big_red_rides_app.create

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CreateViewModel @Inject constructor(): ViewModel() {
    private val _uiStateFlow = MutableStateFlow(UiState())
    val uiState = _uiStateFlow.asStateFlow()

    data class UiState(
        val from:  String = "",
        val to: String = "",
        val date: String = "",
        val departureTime: String = "",
        val arrivalTime: String = "",
        val seatsAvailable: String = "",
        val rideFee: String = "",
        val createSuccess: Boolean = false
    )

    fun onFromChanged(value: String) {
        _uiStateFlow.value = _uiStateFlow.value.copy(from = value)
    }

    fun onToChanged(value: String) {
        _uiStateFlow.value = _uiStateFlow.value.copy(to = value)
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

    fun onRideFeeChanged(value: String) {
        _uiStateFlow.value = _uiStateFlow.value.copy(rideFee = value)
    }

    fun onCreateCLicked() {
        //heres wehre i would send data to the backend
        _uiStateFlow.value = _uiStateFlow.value.copy(createSuccess = true)
    }


}