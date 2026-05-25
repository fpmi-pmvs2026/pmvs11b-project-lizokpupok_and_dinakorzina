package com.example.travelplanner.ui.budget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.travelplanner.presentation.budget.BudgetUiState
import com.example.travelplanner.ui.components.BudgetSummaryCard
import com.example.travelplanner.ui.components.EmptyState
import com.example.travelplanner.ui.components.TravelTopBar
import com.example.travelplanner.ui.components.formatMoney

@Composable
fun BudgetScreen(
    uiState: BudgetUiState,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TravelTopBar(
                title = "Budget",
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        val trip = uiState.trip
        val budgetInfo = uiState.budgetInfo

        if (trip == null || budgetInfo == null) {
            EmptyState(
                title = "Budget unavailable",
                message = "Choose a trip to see planned expenses.",
                modifier = Modifier.padding(innerPadding)
            )
        } else {
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = trip.title,
                    style = MaterialTheme.typography.titleLarge
                )
                BudgetSummaryCard(budgetInfo = budgetInfo)
                Text(
                    text = "Expenses by category",
                    style = MaterialTheme.typography.titleMedium
                )
                uiState.categoryExpenses.forEach { (category, amount) ->
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = category.displayName,
                                style = MaterialTheme.typography.titleSmall
                            )
                            Text(text = formatMoney(amount))
                        }
                    }
                }
            }
        }
    }
}
