package com.example.travelplanner.data.local.repository

import com.example.travelplanner.data.local.dao.TripDao
import com.example.travelplanner.data.local.mapper.toDomain
import com.example.travelplanner.data.local.mapper.toEntity
import com.example.travelplanner.domain.model.Place
import com.example.travelplanner.domain.model.Trip
import com.example.travelplanner.domain.repository.TripRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

/**
 * Реализация TripRepository на основе Room.
 * Write-методы запускаются в [scope] на Dispatchers.IO,
 * так как интерфейс не объявлен suspend.
 */
class RoomTripRepository(
    private val dao: TripDao,
    private val scope: CoroutineScope
) : TripRepository {

    override fun getTrips(): Flow<List<Trip>> =
        dao.observeTrips().map { list -> list.map { it.toDomain() } }

    override fun getTripById(id: Long): Flow<Trip?> =
        dao.observeTripWithPlaces(id).map { it?.toDomain() }

    override fun addTrip(trip: Trip) {
        scope.launch(Dispatchers.IO) { dao.insertTrip(trip.toEntity()) }
    }

    override fun updateTrip(trip: Trip) {
        scope.launch(Dispatchers.IO) { dao.updateTrip(trip.toEntity()) }
    }

    override fun deleteTrip(id: Long) {
        scope.launch(Dispatchers.IO) { dao.deleteTripById(id) }
    }

    override fun addPlace(tripId: Long, place: Place) {
        scope.launch(Dispatchers.IO) {
            dao.insertPlace(place.copy(tripId = tripId).toEntity())
        }
    }

    override fun updatePlace(place: Place) {
        scope.launch(Dispatchers.IO) { dao.updatePlace(place.toEntity()) }
    }

    override fun deletePlace(placeId: Long) {
        scope.launch(Dispatchers.IO) { dao.deletePlaceById(placeId) }
    }

    override fun togglePlaceVisited(placeId: Long) {
        scope.launch(Dispatchers.IO) {
            val current = dao.getPlaceVisited(placeId) ?: return@launch
            dao.updatePlaceVisited(placeId, !current)
        }
    }
}
