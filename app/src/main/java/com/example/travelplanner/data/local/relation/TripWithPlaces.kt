package com.example.travelplanner.data.local.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.example.travelplanner.data.local.entity.PlaceEntity
import com.example.travelplanner.data.local.entity.TripEntity

data class TripWithPlaces(
    @Embedded val trip: TripEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "tripId"
    )
    val places: List<PlaceEntity>
)
