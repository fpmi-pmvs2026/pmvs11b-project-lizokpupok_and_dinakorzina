package com.example.travelplanner.domain.repository

import com.example.travelplanner.domain.model.Place
import com.example.travelplanner.domain.model.Trip
import kotlinx.coroutines.flow.Flow

interface TripRepository {
    fun getTrips(): Flow<List<Trip>>
    fun getTripById(id: Long): Flow<Trip?>
    fun addTrip(trip: Trip)
    fun updateTrip(trip: Trip)
    fun deleteTrip(id: Long)
    fun addPlace(tripId: Long, place: Place)
    fun updatePlace(place: Place)
    fun deletePlace(placeId: Long)
    fun togglePlaceVisited(placeId: Long)
}
