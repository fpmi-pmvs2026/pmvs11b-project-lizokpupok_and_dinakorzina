package com.example.travelplanner.domain.usecase

data class TripFormValidationResult(
    val titleError: String? = null,
    val destinationError: String? = null,
    val budgetError: String? = null
) {
    val isValid: Boolean
        get() = titleError == null && destinationError == null && budgetError == null
}

class TripFormValidator {
    fun validate(title: String, destination: String, budgetText: String): TripFormValidationResult {
        val budget = budgetText.toDoubleOrNull()

        return TripFormValidationResult(
            titleError = if (title.isBlank()) "Trip title is required" else null,
            destinationError = if (destination.isBlank()) "Destination is required" else null,
            budgetError = when {
                budget == null -> "Budget must be a number"
                budget < 0 -> "Budget cannot be negative"
                else -> null
            }
        )
    }
}
