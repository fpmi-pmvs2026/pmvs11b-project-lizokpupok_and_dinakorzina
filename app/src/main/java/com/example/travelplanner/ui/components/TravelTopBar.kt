package com.example.travelplanner.ui.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TravelTopBar(
    title: String,
    onBackClick: (() -> Unit)? = null,
    onAboutClick: (() -> Unit)? = null
) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            if (onBackClick != null) {
                TextButton(onClick = onBackClick) {
                    Text("Back")
                }
            }
        },
        actions = {
            if (onAboutClick != null) {
                TextButton(onClick = onAboutClick) {
                    Text("About")
                }
            }
        }
    )
}
