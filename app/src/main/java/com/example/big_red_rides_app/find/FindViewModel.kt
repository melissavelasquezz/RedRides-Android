package com.example.big_red_rides_app.find

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cornellappdev.introandroid.a6.util.UIEvent
import com.example.big_red_rides_app.rides.Ride
import com.example.big_red_rides_app.rides.mockRides
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FindViewModel @Inject constructor(): ViewModel() {
    private val _uiStateFlow = MutableStateFlow(UiState())
    val uiStateFlow = _uiStateFlow.asStateFlow()

    data class UiState(
        val loading: Boolean = false,
        val from: String = "",
        val to : String = "",
        val date : String = "",
        val navEvent: UIEvent<String>? = null,
        val rideResults: List<Ride> = emptyList()
    )

    fun onFromChanged(from: String){
        _uiStateFlow.value = _uiStateFlow.value.copy(from = from)
    }

    fun onToChanged(to: String) {
        _uiStateFlow.value = _uiStateFlow.value.copy(to = to)
    }
    fun onDateChanged(date: String) {
        _uiStateFlow.value = _uiStateFlow.value.copy(date = date)
    }
    //mock rideresults from a search
    fun onSearch() {
        _uiStateFlow.value = _uiStateFlow.value.copy(loading = true)
        viewModelScope.launch {
            delay(1000)

            _uiStateFlow.value = _uiStateFlow.value.copy(
                loading = false,
                rideResults = mockRides
            )
        }
    }

    fun swapLocations() {
        val currentFrom = _uiStateFlow.value.from
        val currentTo = _uiStateFlow.value.to
        _uiStateFlow.value = _uiStateFlow.value.copy(
            from = currentTo,
            to = currentFrom
        )
    }
}