package com.example.travelplanner.domain.usecase

data class PlaceFormValidationResult(
    val titleError: String? = null,
    val costError: String? = null
) {
    val isValid: Boolean
        get() = titleError == null && costError == null
}

class PlaceFormValidator {
    fun validate(title: String, costText: String): PlaceFormValidationResult {
        val cost = costText.toDoubleOrNull()

        return PlaceFormValidationResult(
            titleError = if (title.isBlank()) "Place title is required" else null,
            costError = when {
                cost == null -> "Cost must be a number"
                cost < 0 -> "Cost cannot be negative"
                else -> null
            }
        )
    }
}
