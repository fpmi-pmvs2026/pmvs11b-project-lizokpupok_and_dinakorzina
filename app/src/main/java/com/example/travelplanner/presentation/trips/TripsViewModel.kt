package com.example.travelplanner.presentation.trips

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelplanner.domain.repository.TripRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class TripsViewModel(
    repository: TripRepository
) : ViewModel() {
    val uiState: StateFlow<TripsUiState> = repository.getTrips()
        .map { trips ->
            TripsUiState(
                trips = trips,
                isLoading = false
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = TripsUiState()
        )
}
