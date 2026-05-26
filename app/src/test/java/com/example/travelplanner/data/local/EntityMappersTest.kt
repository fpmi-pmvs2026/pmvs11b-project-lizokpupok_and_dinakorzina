package com.example.travelplanner.data.local

import com.example.travelplanner.data.local.entity.PlaceEntity
import com.example.travelplanner.data.local.entity.TripEntity
import com.example.travelplanner.data.local.mapper.toDomain
import com.example.travelplanner.data.local.mapper.toEntity
import com.example.travelplanner.data.local.relation.TripWithPlaces
import com.example.travelplanner.domain.model.Place
import com.example.travelplanner.domain.model.PlaceCategory
import com.example.travelplanner.domain.model.Trip
import org.junit.Assert.assertEquals
import org.junit.Test

class EntityMappersTest {

    private val tripEntity = TripEntity(
        id = 1L,
        title = "Prague Trip",
        destination = "Czech Republic",
        startDate = "2026-04-12",
        endDate = "2026-04-18",
        budget = 850.0,
        note = "Walking tour"
    )

    private val placeEntity = PlaceEntity(
        id = 10L,
        tripId = 1L,
        title = "Charles Bridge",
        description = "Morning walk",
        estimatedCost = 0.0,
        category = "SIGHTSEEING",
        isVisited = false,
        note = ""
    )

    @Test
    fun `TripEntity toDomain без мест возвращает пустой список`() {
        val trip = tripEntity.toDomain()
        assertEquals(1L, trip.id)
        assertEquals("Prague Trip", trip.title)
        assertEquals(850.0, trip.budget, 0.001)
        assertEquals(emptyList<Place>(), trip.places)
    }

    @Test
    fun `PlaceEntity toDomain конвертирует категорию`() {
        val place = placeEntity.toDomain()
        assertEquals(10L, place.id)
        assertEquals(PlaceCategory.SIGHTSEEING, place.category)
        assertEquals(false, place.isVisited)
    }

    @Test
    fun `PlaceEntity toDomain с неизвестной категорией возвращает OTHER`() {
        val entity = placeEntity.copy(category = "UNKNOWN_CATEGORY")
        val place = entity.toDomain()
        assertEquals(PlaceCategory.OTHER, place.category)
    }

    @Test
    fun `Trip toEntity сохраняет все поля`() {
        val trip = Trip(
            id = 5L, title = "Istanbul", destination = "Turkey",
            startDate = "2026-06-05", endDate = "2026-06-08",
            budget = 620.0, note = "Ferry route"
        )
        val entity = trip.toEntity()
        assertEquals(5L, entity.id)
        assertEquals("Istanbul", entity.title)
        assertEquals(620.0, entity.budget, 0.001)
    }

    @Test
    fun `Place toEntity хранит категорию как строку`() {
        val place = Place(
            id = 3L, tripId = 1L, title = "Hagia Sophia",
            description = "", estimatedCost = 25.0,
            category = PlaceCategory.SIGHTSEEING, isVisited = false
        )
        val entity = place.toEntity()
        assertEquals("SIGHTSEEING", entity.category)
    }

    @Test
    fun `TripWithPlaces toDomain включает все места`() {
        val relation = TripWithPlaces(trip = tripEntity, places = listOf(placeEntity))
        val trip = relation.toDomain()
        assertEquals(1, trip.places.size)
        assertEquals("Charles Bridge", trip.places[0].title)
    }
}
