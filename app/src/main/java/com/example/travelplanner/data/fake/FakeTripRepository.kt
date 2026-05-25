package com.example.travelplanner.data.fake

import com.example.travelplanner.domain.model.Place
import com.example.travelplanner.domain.model.PlaceCategory
import com.example.travelplanner.domain.model.Trip
import com.example.travelplanner.domain.repository.TripRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

class FakeTripRepository(
    initialTrips: List<Trip> = sampleTrips()
) : TripRepository {
    private val trips = MutableStateFlow(initialTrips)
    private var nextTripId = (initialTrips.maxOfOrNull { it.id } ?: 0L) + 1
    private var nextPlaceId = (
        initialTrips.flatMap { trip -> trip.places }.maxOfOrNull { it.id } ?: 0L
    ) + 1

    override fun getTrips(): Flow<List<Trip>> = trips

    override fun getTripById(id: Long): Flow<Trip?> = trips.map { list ->
        list.firstOrNull { it.id == id }
    }

    override fun addTrip(trip: Trip) {
        val tripToAdd = trip.copy(id = nextTripId++)
        trips.update { current -> current + tripToAdd }
    }

    override fun updateTrip(trip: Trip) {
        trips.update { current ->
            current.map { existing ->
                if (existing.id == trip.id) trip.copy(places = existing.places) else existing
            }
        }
    }

    override fun deleteTrip(id: Long) {
        trips.update { current -> current.filterNot { it.id == id } }
    }

    override fun addPlace(tripId: Long, place: Place) {
        val placeToAdd = place.copy(
            id = nextPlaceId++,
            tripId = tripId
        )

        trips.update { current ->
            current.map { trip ->
                if (trip.id == tripId) {
                    trip.copy(places = trip.places + placeToAdd)
                } else {
                    trip
                }
            }
        }
    }

    override fun updatePlace(place: Place) {
        trips.update { current ->
            current.map { trip ->
                trip.copy(
                    places = trip.places.map { existing ->
                        if (existing.id == place.id) place else existing
                    }
                )
            }
        }
    }

    override fun deletePlace(placeId: Long) {
        trips.update { current ->
            current.map { trip ->
                trip.copy(places = trip.places.filterNot { it.id == placeId })
            }
        }
    }

    override fun togglePlaceVisited(placeId: Long) {
        trips.update { current ->
            current.map { trip ->
                trip.copy(
                    places = trip.places.map { place ->
                        if (place.id == placeId) {
                            place.copy(isVisited = !place.isVisited)
                        } else {
                            place
                        }
                    }
                )
            }
        }
    }

    companion object {
        private fun sampleTrips(): List<Trip> = listOf(
            Trip(
                id = 1,
                title = "Spring in Prague",
                destination = "Prague, Czech Republic",
                startDate = "2026-04-12",
                endDate = "2026-04-18",
                budget = 850.0,
                note = "Book tickets for the old town walking tour.",
                places = listOf(
                    Place(
                        id = 1,
                        tripId = 1,
                        title = "Charles Bridge",
                        description = "Early morning walk before it gets crowded.",
                        estimatedCost = 0.0,
                        category = PlaceCategory.SIGHTSEEING,
                        isVisited = false
                    ),
                    Place(
                        id = 2,
                        tripId = 1,
                        title = "National Museum",
                        description = "Main historical building.",
                        estimatedCost = 18.0,
                        category = PlaceCategory.MUSEUM,
                        isVisited = false
                    )
                )
            ),
            Trip(
                id = 2,
                title = "Istanbul Weekend",
                destination = "Istanbul, Turkey",
                startDate = "2026-06-05",
                endDate = "2026-06-08",
                budget = 620.0,
                note = "Try local food and plan ferry route.",
                places = listOf(
                    Place(
                        id = 3,
                        tripId = 2,
                        title = "Hagia Sophia",
                        description = "Historic district.",
                        estimatedCost = 25.0,
                        category = PlaceCategory.SIGHTSEEING
                    ),
                    Place(
                        id = 4,
                        tripId = 2,
                        title = "Kadikoy food walk",
                        description = "Street food and coffee places.",
                        estimatedCost = 55.0,
                        category = PlaceCategory.FOOD
                    )
                )
            )
        )
    }
}
