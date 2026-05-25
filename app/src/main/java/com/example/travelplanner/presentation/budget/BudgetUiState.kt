package com.example.travelplanner.presentation.budget

import com.example.travelplanner.domain.model.BudgetInfo
import com.example.travelplanner.domain.model.PlaceCategory
import com.example.travelplanner.domain.model.Trip

data class BudgetUiState(
    val trip: Trip? = null,
    val budgetInfo: BudgetInfo? = null,
    val categoryExpenses: Map<PlaceCategory, Double> = emptyMap(),
    val isLoading: Boolean = true
)
