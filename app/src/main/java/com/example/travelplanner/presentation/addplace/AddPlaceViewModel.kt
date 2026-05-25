package com.example.travelplanner.presentation.addplace

import androidx.lifecycle.ViewModel
import com.example.travelplanner.domain.model.Place
import com.example.travelplanner.domain.model.PlaceCategory
import com.example.travelplanner.domain.repository.TripRepository
import com.example.travelplanner.domain.usecase.PlaceFormValidator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class AddPlaceViewModel(
    private val tripId: Long,
    private val repository: TripRepository,
    private val validator: PlaceFormValidator = PlaceFormValidator()
) : ViewModel() {
    private val _uiState = MutableStateFlow(AddPlaceUiState())
    val uiState: StateFlow<AddPlaceUiState> = _uiState

    fun onTitleChange(value: String) {
        _uiState.update { it.copy(title = value, titleError = null) }
    }

    fun onDescriptionChange(value: String) {
        _uiState.update { it.copy(description = value) }
    }

    fun onEstimatedCostChange(value: String) {
        _uiState.update { it.copy(estimatedCost = value, costError = null) }
    }

    fun onCategorySelected(value: PlaceCategory) {
        _uiState.update { it.copy(category = value) }
    }

    fun onNoteChange(value: String) {
        _uiState.update { it.copy(note = value) }
    }

    fun onSaveClick() {
        val state = _uiState.value
        val validation = validator.validate(
            title = state.title,
            costText = state.estimatedCost
        )

        if (!validation.isValid) {
            _uiState.update {
                it.copy(
                    titleError = validation.titleError,
                    costError = validation.costError
                )
            }
            return
        }

        repository.addPlace(
            tripId = tripId,
            place = Place(
                tripId = tripId,
                title = state.title.trim(),
                description = state.description.trim(),
                estimatedCost = state.estimatedCost.toDouble(),
                category = state.category,
                note = state.note.trim()
            )
        )

        _uiState.update { it.copy(isSaved = true) }
    }
}
