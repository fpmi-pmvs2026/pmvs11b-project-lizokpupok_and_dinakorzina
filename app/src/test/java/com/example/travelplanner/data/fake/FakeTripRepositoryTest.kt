package com.example.travelplanner.data.fake

import com.example.travelplanner.domain.model.Place
import com.example.travelplanner.domain.model.PlaceCategory
import com.example.travelplanner.domain.model.Trip
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class FakeTripRepositoryTest {
    @Test
    fun addTripStoresTrip() = runBlocking {
        val repository = FakeTripRepository(initialTrips = emptyList())

        repository.addTrip(testTrip())

        val trips = repository.getTrips().first()
        assertEquals(1, trips.size)
        assertEquals("Test trip", trips.first().title)
    }

    @Test
    fun deleteTripRemovesTrip() = runBlocking {
        val repository = FakeTripRepository(initialTrips = listOf(testTrip(id = 1)))

        repository.deleteTrip(1)

        assertTrue(repository.getTrips().first().isEmpty())
    }

    @Test
    fun addPlaceAddsPlaceToTrip() = runBlocking {
        val repository = FakeTripRepository(initialTrips = listOf(testTrip(id = 1)))

        repository.addPlace(
            tripId = 1,
            place = testPlace()
        )

        val trip = repository.getTrips().first().first()
        assertEquals(1, trip.places.size)
        assertEquals("Cafe", trip.places.first().title)
    }

    @Test
    fun togglePlaceVisitedChangesVisitedFlag() = runBlocking {
        val repository = FakeTripRepository(
            initialTrips = listOf(
                testTrip(
                    id = 1,
                    places = listOf(testPlace(id = 10, tripId = 1, isVisited = false))
                )
            )
        )

        repository.togglePlaceVisited(10)

        val place = repository.getTrips().first().first().places.first()
        assertTrue(place.isVisited)

        repository.togglePlaceVisited(10)

        val toggledAgain = repository.getTrips().first().first().places.first()
        assertFalse(toggledAgain.isVisited)
    }

    private fun testTrip(
        id: Long = 0,
        places: List<Place> = emptyList()
    ): Trip = Trip(
        id = id,
        title = "Test trip",
        destination = "Prague",
        startDate = "2026-06-01",
        endDate = "2026-06-05",
        budget = 500.0,
        note = "",
        places = places
    )

    private fun testPlace(
        id: Long = 0,
        tripId: Long = 0,
        isVisited: Boolean = false
    ): Place = Place(
        id = id,
        tripId = tripId,
        title = "Cafe",
        description = "Breakfast",
        estimatedCost = 20.0,
        category = PlaceCategory.FOOD,
        isVisited = isVisited
    )
}
