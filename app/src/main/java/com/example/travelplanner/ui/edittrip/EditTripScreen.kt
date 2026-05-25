package com.example.travelplanner.ui.edittrip

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.travelplanner.presentation.edittrip.EditTripUiState
import com.example.travelplanner.ui.components.ErrorText
import com.example.travelplanner.ui.components.PrimaryActionButton
import com.example.travelplanner.ui.components.TravelTopBar

@Composable
fun EditTripScreen(
    uiState: EditTripUiState,
    onBackClick: () -> Unit,
    onTitleChange: (String) -> Unit,
    onDestinationChange: (String) -> Unit,
    onStartDateChange: (String) -> Unit,
    onEndDateChange: (String) -> Unit,
    onBudgetChange: (String) -> Unit,
    onNoteChange: (String) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TravelTopBar(
                title = if (uiState.isEditing) "Edit trip" else "New trip",
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(
                value = uiState.title,
                onValueChange = onTitleChange,
                label = { Text("Trip title") },
                singleLine = true,
                isError = uiState.titleError != null
            )
            ErrorText(uiState.titleError)
            OutlinedTextField(
                value = uiState.destination,
                onValueChange = onDestinationChange,
                label = { Text("City / country") },
                singleLine = true,
                isError = uiState.destinationError != null
            )
            ErrorText(uiState.destinationError)
            OutlinedTextField(
                value = uiState.startDate,
                onValueChange = onStartDateChange,
                label = { Text("Start date, e.g. 2026-06-01") },
                singleLine = true
            )
            OutlinedTextField(
                value = uiState.endDate,
                onValueChange = onEndDateChange,
                label = { Text("End date, e.g. 2026-06-07") },
                singleLine = true
            )
            OutlinedTextField(
                value = uiState.budget,
                onValueChange = onBudgetChange,
                label = { Text("Budget") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                isError = uiState.budgetError != null
            )
            ErrorText(uiState.budgetError)
            OutlinedTextField(
                value = uiState.note,
                onValueChange = onNoteChange,
                label = { Text("Note") },
                minLines = 3
            )
            PrimaryActionButton(
                text = "Save",
                onClick = onSaveClick
            )
        }
    }
}
