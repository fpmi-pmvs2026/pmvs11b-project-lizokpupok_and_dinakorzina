package com.example.travelplanner.navigation

object Routes {
    const val TRIPS = "trips"
    const val ADD_TRIP = "add_trip"
    const val ABOUT = "about"

    const val TRIP_DETAILS = "trip_details/{tripId}"
    const val EDIT_TRIP = "edit_trip/{tripId}"
    const val ADD_PLACE = "add_place/{tripId}"
    const val BUDGET = "budget/{tripId}"

    fun tripDetails(tripId: Long): String = "trip_details/$tripId"
    fun editTrip(tripId: Long): String = "edit_trip/$tripId"
    fun addPlace(tripId: Long): String = "add_place/$tripId"
    fun budget(tripId: Long): String = "budget/$tripId"
}
