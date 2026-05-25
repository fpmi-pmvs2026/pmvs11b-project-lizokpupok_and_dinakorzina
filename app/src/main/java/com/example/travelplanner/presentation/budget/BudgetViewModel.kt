package com.example.travelplanner.presentation.budget

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelplanner.domain.repository.TripRepository
import com.example.travelplanner.domain.usecase.CalculateBudgetInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BudgetViewModel(
    tripId: Long,
    repository: TripRepository,
    private val calculateBudgetInfo: CalculateBudgetInfo = CalculateBudgetInfo()
) : ViewModel() {
    private val _uiState = MutableStateFlow(BudgetUiState())
    val uiState: StateFlow<BudgetUiState> = _uiState

    init {
        viewModelScope.launch {
            repository.getTripById(tripId).collect { trip ->
                _uiState.value = BudgetUiState(
                    trip = trip,
                    budgetInfo = trip?.let { calculateBudgetInfo(it.budget, it.places) },
                    categoryExpenses = trip?.places
                        ?.groupBy { it.category }
                        ?.mapValues { entry -> entry.value.sumOf { it.estimatedCost } }
                        .orEmpty(),
                    isLoading = false
                )
            }
        }
    }
}
