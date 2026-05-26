package com.example.travelplanner.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trips")
data class TripEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val destination: String,
    val startDate: String,
    val endDate: String,
    val budget: Double,
    val note: String
)
