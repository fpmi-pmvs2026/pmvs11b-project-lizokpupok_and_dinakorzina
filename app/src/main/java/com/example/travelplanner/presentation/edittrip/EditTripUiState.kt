package com.example.travelplanner.presentation.edittrip

data class EditTripUiState(
    val tripId: Long? = null,
    val title: String = "",
    val destination: String = "",
    val startDate: String = "",
    val endDate: String = "",
    val budget: String = "",
    val note: String = "",
    val titleError: String? = null,
    val destinationError: String? = null,
    val budgetError: String? = null,
    val isSaved: Boolean = false
) {
    val isEditing: Boolean
        get() = tripId != null
}
