package com.example.travelplanner.domain.model

data class BudgetInfo(
    val totalBudget: Double,
    val plannedExpenses: Double,
    val remainingBudget: Double,
    val isOverBudget: Boolean
)
