package com.example.travelplanner.presentation.destination

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelplanner.domain.repository.DestinationInfoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DestinationInfoViewModel(
    private val repository: DestinationInfoRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(DestinationInfoUiState())
    val uiState: StateFlow<DestinationInfoUiState> = _uiState.asStateFlow()

    fun loadDestinationInfo(destination: String) {
        if (destination.isBlank()) return
        _uiState.update { it.copy(isLoading = true, errorMessage = null) }
        viewModelScope.launch {
            repository.getDestinationInfo(destination)
                .onSuccess { info ->
                    _uiState.update { it.copy(isLoading = false, data = info) }
                }
                .onFailure { e ->
                    _uiState.update {
                        it.copy(isLoading = false, errorMessage = e.message ?: "Ошибка")
                    }
                }
        }
    }
}
