package com.example.travelplanner.presentation.addplace

import com.example.travelplanner.domain.model.PlaceCategory

data class AddPlaceUiState(
    val title: String = "",
    val description: String = "",
    val estimatedCost: String = "",
    val category: PlaceCategory = PlaceCategory.SIGHTSEEING,
    val note: String = "",
    val titleError: String? = null,
    val costError: String? = null,
    val isSaved: Boolean = false
)
