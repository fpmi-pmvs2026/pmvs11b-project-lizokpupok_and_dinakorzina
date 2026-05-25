package com.example.travelplanner.domain.model

data class Place(
    val id: Long = 0,
    val tripId: Long = 0,
    val title: String,
    val description: String,
    val estimatedCost: Double,
    val category: PlaceCategory,
    val isVisited: Boolean = false,
    val note: String = ""
)
