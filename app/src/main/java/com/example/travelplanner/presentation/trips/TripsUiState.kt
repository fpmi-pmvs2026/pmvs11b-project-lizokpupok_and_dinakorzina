package com.example.travelplanner.presentation.trips

import com.example.travelplanner.domain.model.Trip

data class TripsUiState(
    val trips: List<Trip> = emptyList(),
    val isLoading: Boolean = true,
    val selectedTripId: Long? = null
) {
    val isEmpty: Boolean
        get() = !isLoading && trips.isEmpty()
}
