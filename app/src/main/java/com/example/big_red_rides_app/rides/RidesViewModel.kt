package com.example.big_red_rides_app.rides

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.big_red_rides_app.retrofit.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RidesViewModel @Inject constructor(
    private val retrofitInstance: RetrofitInstance
) : ViewModel() {
    private val _uiStateFlow = MutableStateFlow(UiState())
    val uiStateFlow = _uiStateFlow

    data class UiState(
        val rideRequests: List<RideRequest> = emptyList(),
        val rideRequestDisplays: List<RideDisplay> = emptyList(),
        val rides: List<Ride> = emptyList(),
        val users: List<User> = emptyList(),
        val loading: Boolean = true,
        val errorMessage: String? = null,
        val yourRides: List<Ride> = emptyList()
    )

    //mock data used for the sake of the video
    private fun loadMockData() {
        val driverRide = Ride(
            id = 3,
            departureCity = "Ithaca",
            arrivalCity = "Syracuse",
            departureTime = "11:00 AM",
            arrivalTime = "2:00 PM",
            date = "05/02/2025",
            availableSeats = 3,
            price = 10,
            driverId = 3
        )

        val requestedRide = Ride(
            id = 1,
            departureCity = "Ithaca",
            arrivalCity = "Syracuse",
            departureTime = "9:00 AM",
            arrivalTime = "1:00 PM",
            date = "05/02/2025",
            availableSeats = 3,
            price = 20,
            driverId = 1
        )

        val requestMock = RideRequest(
            id = 100,
            rideId = 1,
            passengerId = 3,
            status = "pending"
        )

        val mockDriver = User(
            id = 1,
            name = "Melissa Velasquez",
            email = "mv477@cornell.edu",
            phone = "508-514-9485",
            grad_year = 2027,
            password = "1234",
            requests = emptyList(),
            rides = listOf(requestedRide)
        )

        val mockPassenger = User(
            id = 3,
            name = "Ashley Herrera",
            email = "ah999@cornell.edu",
            phone = "123-456-7890",
            grad_year = 2026,
            password = "1234",
            requests = listOf(requestMock),
            rides = listOf(driverRide)
        )

        _uiStateFlow.value = UiState(
            yourRides = listOf(driverRide),
            rideRequests = listOf(requestMock),
            rides = listOf(driverRide, requestedRide),
            users = listOf(mockDriver, mockPassenger),
            loading = false,
            errorMessage = null
        )
    }

    fun loadData(currentUserID: Int) {
        viewModelScope.launch {
            _uiStateFlow.value = _uiStateFlow.value.copy(loading = true)
            try {
                loadMockData() // Replaced real API call with mock
            } catch (e: Exception) {
                _uiStateFlow.value = UiState(
                    loading = false,
                    errorMessage = "Failed to load ride data."
                )
            }
        }
    }

    fun cancelRequest(requestId: Int) {
        viewModelScope.launch {
            try {
                retrofitInstance.requestApiService.deleteRequest(requestId)
                _uiStateFlow.value = _uiStateFlow.value.copy(
                    rideRequests = _uiStateFlow.value.rideRequests.filterNot { it.id == requestId }
                )
            } catch (e: Exception) {
                _uiStateFlow.value = _uiStateFlow.value.copy(
                    errorMessage = "Failed to cancel request."
                )
            }
        }
    }

    // did not use in the video
    fun acceptRequest(requestId: Int) {
        viewModelScope.launch {
            try {
                retrofitInstance.requestApiService.resolveRequest(
                    requestId = requestId,
                    body = mapOf("status" to "yes")
                )
                _uiStateFlow.value = _uiStateFlow.value.copy(
                    rideRequestDisplays = _uiStateFlow.value.rideRequestDisplays.map {
                        if (it.request.id == requestId) {
                            it.copy(request = it.request.copy(status = "yes"))
                        } else {
                            it
                        }
                    }
                )
            } catch (e: Exception) {
                _uiStateFlow.value = _uiStateFlow.value.copy(
                    errorMessage = "Failed to accept request."
                )
            }
        }
    }

    //for the sake of the video
    init {
        loadMockData()
    }
}
