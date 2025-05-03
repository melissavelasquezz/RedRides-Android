package com.example.big_red_rides_app.find

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.big_red_rides_app.Screen
import com.example.big_red_rides_app.retrofit.RetrofitInstance
import com.example.big_red_rides_app.retrofit.Ride
import com.example.big_red_rides_app.retrofit.User
import com.example.big_red_rides_app.util.UIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FindViewModel @Inject constructor(
    private val retrofitInstance: RetrofitInstance
): ViewModel() {

    private val _uiStateFlow = MutableStateFlow(UiState())
    val uiStateFlow = _uiStateFlow.asStateFlow()

    data class UiState(
        val loading: Boolean = false,
        val from: String = "",
        val to : String = "",
        val date : String = "",
        val navEvent: UIEvent<Screen>? = null,
        val rideResults: List<Ride> = emptyList(),
        val users: List<User> = emptyList(),
        val errorMessage: String? = null
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

    fun swapLocations() {
        val currentFrom = _uiStateFlow.value.from
        val currentTo = _uiStateFlow.value.to
        _uiStateFlow.value = _uiStateFlow.value.copy(
            from = currentTo,
            to = currentFrom
        )
    }

    fun onRideCardClicked(rideId: Int) {
        _uiStateFlow.value = _uiStateFlow.value.copy(
            navEvent = UIEvent(Screen.DetailsScreen(rideId))
        )
    }

    fun loadMockData() {
        val mockRides = listOf(
            Ride(
                id = 1,
                departureCity = "Ithaca",
                arrivalCity = "Syracuse",
                departureTime = "9:00 AM",
                arrivalTime = "1:00 PM",
                date = "05/02/2025",
                availableSeats = 3,
                price = 20,
                driverId = 1
            ),
            Ride(
                id = 2,
                departureCity = "Ithaca",
                arrivalCity = "Syracuse",
                departureTime = "10:00 AM",
                arrivalTime = "2:00 PM",
                date = "05/02/2025",
                availableSeats = 2,
                price = 25,
                driverId = 2
            ),
            Ride(
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
        )

        val mockUsers = listOf(
            User(
                id =1 ,
                name = "Melissa Velasquez",
                email = "mv477@cornell.edu",
                phone = "508-514-9485",
                grad_year = 2027,
                requests = emptyList(),
                rides = emptyList(),
                password = "1234"
            ),
            User(
                id = 3 ,
                name = "Mark Smith",
                email = "ms131@cornell.edu",
                phone = "508-514-9487",
                grad_year = 2025,
                requests = emptyList(),
                rides = listOf(Ride(
                    id = 3,
                    departureCity = "Ithaca",
                    arrivalCity = "Syracuse",
                    departureTime = "11:00 AM",
                    arrivalTime = "2:00 PM",
                    date = "05/02/2025",
                    availableSeats = 3,
                    price = 10,
                    driverId = 3
                )),
                password = "1234"
            ),
            User(
                id = 2,
                name = "Leo",
                email = "ll865@cornell.edu",
                phone = "508-514-9486",
                grad_year = 2027,
                requests = emptyList(),
                rides = emptyList(),
                password = "1234"
            )
        )

        _uiStateFlow.value = _uiStateFlow.value.copy(
            rideResults = mockRides,
            users = mockUsers,
            loading = false,
            errorMessage = null
        )
    }

    fun onSearch() {
        // commented out to hard code mock data to be displayed for the sake of the final video
//         val state = _uiStateFlow.value
//        _uiStateFlow.value = state.copy(loading = true, errorMessage = null)
//        viewModelScope.launch {
//                val allUsers = retrofitInstance.userApiService.getAllUsers()
//                val allRides = retrofitInstance.rideApiService.getAllRides()
//
//                val filtered = allRides.filter{
//                    it.departureCity.equals(state.from, ignoreCase = true) &&
//                            it.arrivalCity.equals(state.to, ignoreCase = true) &&
//                            it.date.equals(state.date, ignoreCase = true)
//                }
//                _uiStateFlow.value = _uiStateFlow.value.copy(
//                    rideResults = filtered,
//                    users = allUsers,
//                    loading = false
//                )
//
//        }
        _uiStateFlow.value = _uiStateFlow.value.copy(loading = true)
        viewModelScope.launch {
            delay(1000)
            loadMockData()
        }
    }


}