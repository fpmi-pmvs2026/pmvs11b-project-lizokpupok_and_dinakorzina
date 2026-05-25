package com.example.travelplanner.domain.model

data class Trip(
    val id: Long = 0,
    val title: String,
    val destination: String,
    val startDate: String,
    val endDate: String,
    val budget: Double,
    val note: String = "",
    val places: List<Place> = emptyList()
)
