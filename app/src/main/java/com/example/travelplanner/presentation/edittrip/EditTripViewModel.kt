package com.example.travelplanner.presentation.edittrip

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelplanner.domain.model.Trip
import com.example.travelplanner.domain.repository.TripRepository
import com.example.travelplanner.domain.usecase.TripFormValidator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EditTripViewModel(
    private val repository: TripRepository,
    private val tripId: Long? = null,
    private val validator: TripFormValidator = TripFormValidator()
) : ViewModel() {
    private val _uiState = MutableStateFlow(EditTripUiState(tripId = tripId))
    val uiState: StateFlow<EditTripUiState> = _uiState

    init {
        if (tripId != null) {
            viewModelScope.launch {
                val trip = repository.getTripById(tripId).firstOrNull()
                if (trip != null) {
                    _uiState.value = EditTripUiState(
                        tripId = trip.id,
                        title = trip.title,
                        destination = trip.destination,
                        startDate = trip.startDate,
                        endDate = trip.endDate,
                        budget = trip.budget.toString(),
                        note = trip.note
                    )
                }
            }
        }
    }

    fun onTitleChange(value: String) {
        _uiState.update { it.copy(title = value, titleError = null) }
    }

    fun onDestinationChange(value: String) {
        _uiState.update { it.copy(destination = value, destinationError = null) }
    }

    fun onStartDateChange(value: String) {
        _uiState.update { it.copy(startDate = value) }
    }

    fun onEndDateChange(value: String) {
        _uiState.update { it.copy(endDate = value) }
    }

    fun onBudgetChange(value: String) {
        _uiState.update { it.copy(budget = value, budgetError = null) }
    }

    fun onNoteChange(value: String) {
        _uiState.update { it.copy(note = value) }
    }

    fun onSaveClick() {
        val state = _uiState.value
        val validation = validator.validate(
            title = state.title,
            destination = state.destination,
            budgetText = state.budget
        )

        if (!validation.isValid) {
            _uiState.update {
                it.copy(
                    titleError = validation.titleError,
                    destinationError = validation.destinationError,
                    budgetError = validation.budgetError
                )
            }
            return
        }

        val trip = Trip(
            id = state.tripId ?: 0,
            title = state.title.trim(),
            destination = state.destination.trim(),
            startDate = state.startDate.trim(),
            endDate = state.endDate.trim(),
            budget = state.budget.toDouble(),
            note = state.note.trim()
        )

        if (state.isEditing) {
            repository.updateTrip(trip)
        } else {
            repository.addTrip(trip)
        }

        _uiState.update { it.copy(isSaved = true) }
    }
}
