package com.example.travelplanner.domain.usecase

import com.example.travelplanner.domain.model.BudgetInfo
import com.example.travelplanner.domain.model.Place

class CalculateBudgetInfo {
    operator fun invoke(totalBudget: Double, places: List<Place>): BudgetInfo {
        val plannedExpenses = places.sumOf { it.estimatedCost }
        val remainingBudget = totalBudget - plannedExpenses

        return BudgetInfo(
            totalBudget = totalBudget,
            plannedExpenses = plannedExpenses,
            remainingBudget = remainingBudget,
            isOverBudget = remainingBudget < 0
        )
    }
}
