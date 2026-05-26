package com.example.travelplanner.domain.model

data class DestinationInfo(
    val name: String,
    val country: String,
    val capital: String?,
    val region: String?,
    val currency: String?,
    val population: Long?,
    val flagUrl: String?
)
