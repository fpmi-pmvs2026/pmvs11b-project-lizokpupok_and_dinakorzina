package com.example.travelplanner.domain.usecase

import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class TripFormValidatorTest {
    private val validator = TripFormValidator()

    @Test
    fun emptyTitleReturnsError() {
        val result = validator.validate(
            title = "",
            destination = "Prague",
            budgetText = "500"
        )

        assertFalse(result.isValid)
        assertNotNull(result.titleError)
    }

    @Test
    fun emptyDestinationReturnsError() {
        val result = validator.validate(
            title = "Vacation",
            destination = "",
            budgetText = "500"
        )

        assertFalse(result.isValid)
        assertNotNull(result.destinationError)
    }

    @Test
    fun negativeBudgetReturnsError() {
        val result = validator.validate(
            title = "Vacation",
            destination = "Prague",
            budgetText = "-1"
        )

        assertFalse(result.isValid)
        assertNotNull(result.budgetError)
    }

    @Test
    fun validTripFormPassesValidation() {
        val result = validator.validate(
            title = "Vacation",
            destination = "Prague",
            budgetText = "500"
        )

        assertTrue(result.isValid)
        assertNull(result.titleError)
        assertNull(result.destinationError)
        assertNull(result.budgetError)
    }
}
