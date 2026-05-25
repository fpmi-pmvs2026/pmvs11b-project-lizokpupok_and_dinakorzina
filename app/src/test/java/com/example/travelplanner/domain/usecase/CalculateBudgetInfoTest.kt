package com.example.travelplanner.domain.usecase

import com.example.travelplanner.domain.model.Place
import com.example.travelplanner.domain.model.PlaceCategory
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class CalculateBudgetInfoTest {
    private val calculateBudgetInfo = CalculateBudgetInfo()

    @Test
    fun expensesLessThanBudgetLeavesPositiveRemainingBudget() {
        val result = calculateBudgetInfo(
            totalBudget = 100.0,
            places = listOf(testPlace(cost = 20.0), testPlace(cost = 30.0))
        )

        assertEquals(50.0, result.remainingBudget, 0.001)
        assertFalse(result.isOverBudget)
    }

    @Test
    fun expensesEqualBudgetLeavesZeroRemainingBudget() {
        val result = calculateBudgetInfo(
            totalBudget = 100.0,
            places = listOf(testPlace(cost = 40.0), testPlace(cost = 60.0))
        )

        assertEquals(0.0, result.remainingBudget, 0.001)
        assertFalse(result.isOverBudget)
    }

    @Test
    fun expensesGreaterThanBudgetMarksBudgetAsExceeded() {
        val result = calculateBudgetInfo(
            totalBudget = 100.0,
            places = listOf(testPlace(cost = 80.0), testPlace(cost = 50.0))
        )

        assertTrue(result.isOverBudget)
    }

    private fun testPlace(cost: Double): Place = Place(
        id = 1,
        tripId = 1,
        title = "Museum",
        description = "Ticket",
        estimatedCost = cost,
        category = PlaceCategory.MUSEUM
    )
}
