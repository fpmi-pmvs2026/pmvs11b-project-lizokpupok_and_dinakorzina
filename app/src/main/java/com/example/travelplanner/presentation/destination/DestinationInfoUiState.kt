package com.example.travelplanner.presentation.destination

import com.example.travelplanner.domain.model.DestinationInfo

data class DestinationInfoUiState(
    val isLoading: Boolean = false,
    val data: DestinationInfo? = null,
    val errorMessage: String? = null
)
