package com.example.big_red_rides_app.rides
data class Profile(
    val id: Int,
    val name: String,
    val email: String,
    val phone: String,
    val gradYear: Int
)

data class Ride(
    val id: Int,
    val departureCity: String,
    val arrivalCity: String,
    val departureTime: String,
    val arrivalTime: String,
    val date: String,
    val availableSeats: Int,
    val price: Int,
    val driverId: Int,
)

data class RideRequest(
    val rideId: Int, //profileid
    val passengerId: Int, //profileid
    var status: RequestStatus,
    val passengerName: String,
)

enum class RequestStatus {
    REQUESTED,
    ACCEPTED,
    DECLINED
}

//mockdata
val mockProfiles = listOf(
    Profile(1, "Melissa V", "melissa@example.com", "555-1234", 2025),
    Profile(2, "Ashley H", "ashley@example.com", "555-5678", 2026),
    Profile(10, "John Doe", "john@example.com", "555-1111", 2024),
    Profile(11, "Alice Smith", "alice@example.com", "555-2222", 2024),
    Profile(12, "Bob Johnson", "bob@example.com", "555-3333", 2025),
    Profile(13, "Charlie Lee", "charlie@example.com", "555-4444", 2025)
)

val mockRides = listOf(
    Ride(
        id = 1,
        departureCity = "Ithaca",
        arrivalCity = "New York",
        departureTime = "9:00 AM",
        arrivalTime = "1:00 PM",
        date = "5/2",
        availableSeats = 2,
        price = 30,
        driverId = 1
    ),
    Ride(
        id = 2,
        departureCity = "Ithaca",
        arrivalCity = "Boston",
        departureTime = "10:00 AM",
        arrivalTime = "2:30 PM",
        date = "5/3",
        availableSeats = 3,
        price = 40,
        driverId = 2
    ),
    Ride(
        id = 3,
        departureCity = "Syracuse",
        arrivalCity = "Ithaca",
        departureTime = "8:00 AM",
        arrivalTime = "9:30 AM",
        date = "5/2",
        availableSeats = 1,
        price = 20,
        driverId = 1
    )
)

val mockRideRequests = listOf(
    RideRequest(
        rideId = 1,
        passengerId = 10,
        status = RequestStatus.REQUESTED,
        passengerName = "John Doe"
    ),
    RideRequest(
        rideId = 1,
        passengerId = 11,
        status = RequestStatus.REQUESTED,
        passengerName = "Alice Smith"
    ),
    RideRequest(
        rideId = 2,
        passengerId = 12,
        status = RequestStatus.ACCEPTED,
        passengerName = "Bob Johnson"
    ),
    RideRequest(
        rideId = 3,
        passengerId = 13,
        status = RequestStatus.REQUESTED,
        passengerName = "Charlie Lee"
    )
)
