package com.example.travelplanner.ui.trips

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.travelplanner.domain.usecase.GetTripStatus
import com.example.travelplanner.presentation.trips.TripsUiState
import com.example.travelplanner.ui.components.EmptyState
import com.example.travelplanner.ui.components.TravelTopBar
import com.example.travelplanner.ui.components.TripCard

@Composable
fun TripsScreen(
    uiState: TripsUiState,
    onTripClick: (Long) -> Unit,
    onAddTripClick: () -> Unit,
    onAboutClick: () -> Unit,
    modifier: Modifier = Modifier,
    getTripStatus: GetTripStatus = GetTripStatus()
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TravelTopBar(
                title = "Travel Planner",
                onAboutClick = onAboutClick
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddTripClick) {
                Text("+")
            }
        }
    ) { innerPadding ->
        if (uiState.isEmpty) {
            EmptyState(
                title = "No trips yet",
                message = "Create your first trip and start collecting places to visit.",
                modifier = Modifier.padding(innerPadding)
            )
        } else {
            LazyColumn(
                modifier = Modifier.padding(innerPadding),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(uiState.trips, key = { it.id }) { trip ->
                    TripCard(
                        trip = trip,
                        status = getTripStatus(trip),
                        onClick = { onTripClick(trip.id) }
                    )
                }
            }
        }
    }
}
