package com.example.travelplanner.ui.tripdetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.travelplanner.presentation.tripdetails.TripDetailsUiState
import com.example.travelplanner.ui.components.BudgetSummaryCard
import com.example.travelplanner.ui.components.EmptyState
import com.example.travelplanner.ui.components.PlaceCard
import com.example.travelplanner.ui.components.TravelTopBar
import com.example.travelplanner.ui.components.formatMoney

@Composable
fun TripDetailsScreen(
    uiState: TripDetailsUiState,
    onBackClick: () -> Unit,
    onEditClick: (Long) -> Unit,
    onDeleteClick: () -> Unit,
    onAddPlaceClick: (Long) -> Unit,
    onBudgetClick: (Long) -> Unit,
    onPlaceVisitedToggle: (Long) -> Unit,
    onPlaceDeleteClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    val trip = uiState.trip

    Scaffold(
        modifier = modifier,
        topBar = {
            TravelTopBar(
                title = "Trip details",
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        if (trip == null) {
            EmptyState(
                title = "Trip not found",
                message = "This trip may have been deleted.",
                modifier = Modifier.padding(innerPadding)
            )
        } else {
            LazyColumn(
                modifier = Modifier.padding(innerPadding),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(
                            text = trip.title,
                            style = MaterialTheme.typography.titleLarge
                        )
                        Text(text = trip.destination)
                        Text(text = "${trip.startDate} - ${trip.endDate}")
                        Text(text = "Budget: ${formatMoney(trip.budget)}")
                        if (trip.note.isNotBlank()) {
                            Text(
                                text = trip.note,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Button(
                            onClick = { onEditClick(trip.id) },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Edit")
                        }
                        Button(
                            onClick = { onAddPlaceClick(trip.id) },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Add place")
                        }
                    }
                }
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Button(
                            onClick = { onBudgetClick(trip.id) },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Budget")
                        }
                        TextButton(
                            onClick = onDeleteClick,
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Delete")
                        }
                    }
                }
                uiState.budgetInfo?.let { budgetInfo ->
                    item {
                        BudgetSummaryCard(budgetInfo = budgetInfo)
                    }
                }
                item {
                    Text(
                        text = "Places to visit",
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                if (trip.places.isEmpty()) {
                    item {
                        Text(
                            text = "No places added yet.",
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                } else {
                    items(trip.places, key = { it.id }) { place ->
                        PlaceCard(
                            place = place,
                            onVisitedToggle = { onPlaceVisitedToggle(place.id) },
                            onDeleteClick = { onPlaceDeleteClick(place.id) }
                        )
                    }
                }
            }
        }
    }
}
