package com.example.travelplanner

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.travelplanner.navigation.AppNavGraph

@Composable
fun TravelPlannerApp() {
    val navController = rememberNavController()
    AppNavGraph(navController = navController)
}
