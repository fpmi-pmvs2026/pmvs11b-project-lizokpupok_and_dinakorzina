package com.example.travelplanner.ui.about

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.travelplanner.ui.components.TravelTopBar

@Composable
fun AboutScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TravelTopBar(
                title = "About",
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Travel Planner",
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = "Android application for planning trips, places to visit, and expected travel expenses."
            )
            Text(
                text = "Authors",
                style = MaterialTheme.typography.titleMedium
            )
            Text(text = "Participant 1: UI, Compose, navigation, ViewModel, actions, unit tests.")
            Text(text = "Participant 2: Room, API, coroutines integration, GitHub infrastructure, APK and pages.")
            Text(
                text = "Technology stack",
                style = MaterialTheme.typography.titleMedium
            )
            Text(text = "Kotlin, Android, Jetpack Compose, Material 3, Navigation Compose, ViewModel, StateFlow, JUnit.")
            Text(
                text = "Project purpose",
                style = MaterialTheme.typography.titleMedium
            )
            Text(text = "The project demonstrates a multi-screen Android interface, local data integration readiness, asynchronous state flow, and a clean MVVM structure for a university course.")
        }
    }
}
