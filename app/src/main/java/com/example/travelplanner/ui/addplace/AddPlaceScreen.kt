package com.example.travelplanner.ui.addplace

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.travelplanner.domain.model.PlaceCategory
import com.example.travelplanner.presentation.addplace.AddPlaceUiState
import com.example.travelplanner.ui.components.CategoryChips
import com.example.travelplanner.ui.components.ErrorText
import com.example.travelplanner.ui.components.PrimaryActionButton
import com.example.travelplanner.ui.components.TravelTopBar

@Composable
fun AddPlaceScreen(
    uiState: AddPlaceUiState,
    onBackClick: () -> Unit,
    onTitleChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onEstimatedCostChange: (String) -> Unit,
    onCategorySelected: (PlaceCategory) -> Unit,
    onNoteChange: (String) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TravelTopBar(
                title = "Add place",
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
                label = { Text("Place title") },
                singleLine = true,
                isError = uiState.titleError != null
            )
            ErrorText(uiState.titleError)
            OutlinedTextField(
                value = uiState.description,
                onValueChange = onDescriptionChange,
                label = { Text("Address or description") },
                minLines = 2
            )
            OutlinedTextField(
                value = uiState.estimatedCost,
                onValueChange = onEstimatedCostChange,
                label = { Text("Estimated cost") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                isError = uiState.costError != null
            )
            ErrorText(uiState.costError)
            Text(
                text = "Category",
                style = MaterialTheme.typography.titleMedium
            )
            CategoryChips(
                selectedCategory = uiState.category,
                onCategorySelected = onCategorySelected
            )
            OutlinedTextField(
                value = uiState.note,
                onValueChange = onNoteChange,
                label = { Text("Note") },
                minLines = 3
            )
            PrimaryActionButton(
                text = "Save place",
                onClick = onSaveClick
            )
        }
    }
}
