package com.example.travelplanner.data.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.travelplanner.data.local.dao.TripDao
import com.example.travelplanner.data.local.database.AppDatabase
import com.example.travelplanner.data.local.entity.PlaceEntity
import com.example.travelplanner.data.local.entity.TripEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TripDaoTest {

    private lateinit var db: AppDatabase
    private lateinit var dao: TripDao

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        dao = db.tripDao()
    }

    @After
    fun closeDb() = db.close()

    private val testTrip = TripEntity(
        title = "Spring in Prague",
        destination = "Czech Republic",
        startDate = "2026-04-12",
        endDate = "2026-04-18",
        budget = 850.0,
        note = ""
    )

    @Test
    fun insertTrip_shouldSaveTrip() = runTest {
        val id = dao.insertTrip(testTrip)
        val trips = dao.observeTrips().first()
        assertEquals(1, trips.size)
        assertEquals("Spring in Prague", trips[0].title)
        assertTrue(id > 0)
    }

    @Test
    fun insertPlace_shouldAttachPlaceToTrip() = runTest {
        val tripId = dao.insertTrip(testTrip)
        dao.insertPlace(
            PlaceEntity(
                tripId = tripId, title = "Charles Bridge", description = "",
                estimatedCost = 0.0, category = "SIGHTSEEING", isVisited = false, note = ""
            )
        )
        val places = dao.observePlacesForTrip(tripId).first()
        assertEquals(1, places.size)
        assertEquals("Charles Bridge", places[0].title)
    }

    @Test
    fun deleteTrip_shouldDeletePlacesCascade() = runTest {
        val tripId = dao.insertTrip(testTrip)
        dao.insertPlace(
            PlaceEntity(
                tripId = tripId, title = "Museum", description = "",
                estimatedCost = 18.0, category = "MUSEUM", isVisited = false, note = ""
            )
        )
        dao.deleteTripById(tripId)
        assertTrue(dao.observeTrips().first().isEmpty())
        assertTrue(dao.observePlacesForTrip(tripId).first().isEmpty())
    }

    @Test
    fun observeTripWithPlaces_shouldReturnTripWithPlaces() = runTest {
        val tripId = dao.insertTrip(testTrip)
        dao.insertPlace(
            PlaceEntity(
                tripId = tripId, title = "National Museum", description = "",
                estimatedCost = 18.0, category = "MUSEUM", isVisited = false, note = ""
            )
        )
        val result = dao.observeTripWithPlaces(tripId).first()
        assertNotNull(result)
        assertEquals("Spring in Prague", result!!.trip.title)
        assertEquals(1, result.places.size)
    }

    @Test
    fun updatePlaceVisited_shouldToggleFlag() = runTest {
        val tripId = dao.insertTrip(testTrip)
        val placeId = dao.insertPlace(
            PlaceEntity(
                tripId = tripId, title = "Hagia Sophia", description = "",
                estimatedCost = 25.0, category = "SIGHTSEEING", isVisited = false, note = ""
            )
        )
        dao.updatePlaceVisited(placeId, true)
        val places = dao.observePlacesForTrip(tripId).first()
        assertTrue(places[0].isVisited)
    }
}
