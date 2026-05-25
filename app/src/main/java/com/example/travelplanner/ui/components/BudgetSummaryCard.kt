package com.example.travelplanner.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.travelplanner.domain.model.BudgetInfo

@Composable
fun BudgetSummaryCard(
    budgetInfo: BudgetInfo,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Budget summary",
                style = MaterialTheme.typography.titleMedium
            )
            BudgetLine(label = "Total budget", value = formatMoney(budgetInfo.totalBudget))
            BudgetLine(label = "Planned expenses", value = formatMoney(budgetInfo.plannedExpenses))
            BudgetLine(label = "Remaining", value = formatMoney(budgetInfo.remainingBudget))
            if (budgetInfo.isOverBudget) {
                Text(
                    text = "Budget exceeded",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
private fun BudgetLine(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label)
        Text(text = value, style = MaterialTheme.typography.titleSmall)
    }
}
