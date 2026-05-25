package com.example.travelplanner.presentation.tripdetails

import com.example.travelplanner.domain.model.BudgetInfo
import com.example.travelplanner.domain.model.Trip

data class TripDetailsUiState(
    val trip: Trip? = null,
    val budgetInfo: BudgetInfo? = null,
    val isDeleted: Boolean = false,
    val isLoading: Boolean = true
)
