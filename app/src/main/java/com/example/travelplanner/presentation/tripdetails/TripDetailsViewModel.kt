package com.example.travelplanner.presentation.tripdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelplanner.domain.repository.TripRepository
import com.example.travelplanner.domain.usecase.CalculateBudgetInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TripDetailsViewModel(
    private val tripId: Long,
    private val repository: TripRepository,
    private val calculateBudgetInfo: CalculateBudgetInfo = CalculateBudgetInfo()
) : ViewModel() {
    private val _uiState = MutableStateFlow(TripDetailsUiState())
    val uiState: StateFlow<TripDetailsUiState> = _uiState

    init {
        viewModelScope.launch {
            repository.getTripById(tripId).collect { trip ->
                _uiState.value = TripDetailsUiState(
                    trip = trip,
                    budgetInfo = trip?.let { calculateBudgetInfo(it.budget, it.places) },
                    isLoading = false
                )
            }
        }
    }

    fun onDeleteClick() {
        repository.deleteTrip(tripId)
        _uiState.update { it.copy(isDeleted = true) }
    }

    fun onPlaceVisitedToggle(placeId: Long) {
        repository.togglePlaceVisited(placeId)
    }

    fun onPlaceDeleteClick(placeId: Long) {
        repository.deletePlace(placeId)
    }
}
