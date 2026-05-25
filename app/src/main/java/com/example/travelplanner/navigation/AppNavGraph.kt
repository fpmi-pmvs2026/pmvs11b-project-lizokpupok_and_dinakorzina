package com.example.travelplanner.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.travelplanner.AppContainer
import com.example.travelplanner.presentation.addplace.AddPlaceViewModel
import com.example.travelplanner.presentation.budget.BudgetViewModel
import com.example.travelplanner.presentation.edittrip.EditTripViewModel
import com.example.travelplanner.presentation.travelPlannerViewModelFactory
import com.example.travelplanner.presentation.tripdetails.TripDetailsViewModel
import com.example.travelplanner.presentation.trips.TripsViewModel
import com.example.travelplanner.ui.about.AboutScreen
import com.example.travelplanner.ui.addplace.AddPlaceScreen
import com.example.travelplanner.ui.budget.BudgetScreen
import com.example.travelplanner.ui.edittrip.EditTripScreen
import com.example.travelplanner.ui.tripdetails.TripDetailsScreen
import com.example.travelplanner.ui.trips.TripsScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.TRIPS
    ) {
        composable(Routes.TRIPS) {
            val viewModel: TripsViewModel = viewModel(
                factory = travelPlannerViewModelFactory {
                    TripsViewModel(AppContainer.tripRepository)
                }
            )
            val uiState by viewModel.uiState.collectAsState()

            TripsScreen(
                uiState = uiState,
                onTripClick = { tripId ->
                    navController.navigate(Routes.tripDetails(tripId))
                },
                onAddTripClick = {
                    navController.navigate(Routes.ADD_TRIP)
                },
                onAboutClick = {
                    navController.navigate(Routes.ABOUT)
                }
            )
        }

        composable(Routes.ADD_TRIP) {
            val viewModel: EditTripViewModel = viewModel(
                factory = travelPlannerViewModelFactory {
                    EditTripViewModel(AppContainer.tripRepository)
                }
            )
            val uiState by viewModel.uiState.collectAsState()

            LaunchedEffect(uiState.isSaved) {
                if (uiState.isSaved) {
                    navController.popBackStack(Routes.TRIPS, inclusive = false)
                }
            }

            EditTripScreen(
                uiState = uiState,
                onBackClick = { navController.popBackStack() },
                onTitleChange = viewModel::onTitleChange,
                onDestinationChange = viewModel::onDestinationChange,
                onStartDateChange = viewModel::onStartDateChange,
                onEndDateChange = viewModel::onEndDateChange,
                onBudgetChange = viewModel::onBudgetChange,
                onNoteChange = viewModel::onNoteChange,
                onSaveClick = viewModel::onSaveClick
            )
        }

        composable(
            route = Routes.TRIP_DETAILS,
            arguments = listOf(navArgument("tripId") { type = NavType.LongType })
        ) { backStackEntry ->
            val tripId = backStackEntry.arguments?.getLong("tripId") ?: return@composable
            val viewModel: TripDetailsViewModel = viewModel(
                factory = travelPlannerViewModelFactory {
                    TripDetailsViewModel(
                        tripId = tripId,
                        repository = AppContainer.tripRepository
                    )
                }
            )
            val uiState by viewModel.uiState.collectAsState()

            LaunchedEffect(uiState.isDeleted) {
                if (uiState.isDeleted) {
                    navController.popBackStack(Routes.TRIPS, inclusive = false)
                }
            }

            TripDetailsScreen(
                uiState = uiState,
                onBackClick = { navController.popBackStack() },
                onEditClick = { navController.navigate(Routes.editTrip(it)) },
                onDeleteClick = viewModel::onDeleteClick,
                onAddPlaceClick = { navController.navigate(Routes.addPlace(it)) },
                onBudgetClick = { navController.navigate(Routes.budget(it)) },
                onPlaceVisitedToggle = viewModel::onPlaceVisitedToggle,
                onPlaceDeleteClick = viewModel::onPlaceDeleteClick
            )
        }

        composable(
            route = Routes.EDIT_TRIP,
            arguments = listOf(navArgument("tripId") { type = NavType.LongType })
        ) { backStackEntry ->
            val tripId = backStackEntry.arguments?.getLong("tripId") ?: return@composable
            val viewModel: EditTripViewModel = viewModel(
                factory = travelPlannerViewModelFactory {
                    EditTripViewModel(
                        repository = AppContainer.tripRepository,
                        tripId = tripId
                    )
                }
            )
            val uiState by viewModel.uiState.collectAsState()

            LaunchedEffect(uiState.isSaved) {
                if (uiState.isSaved) {
                    navController.popBackStack()
                }
            }

            EditTripScreen(
                uiState = uiState,
                onBackClick = { navController.popBackStack() },
                onTitleChange = viewModel::onTitleChange,
                onDestinationChange = viewModel::onDestinationChange,
                onStartDateChange = viewModel::onStartDateChange,
                onEndDateChange = viewModel::onEndDateChange,
                onBudgetChange = viewModel::onBudgetChange,
                onNoteChange = viewModel::onNoteChange,
                onSaveClick = viewModel::onSaveClick
            )
        }

        composable(
            route = Routes.ADD_PLACE,
            arguments = listOf(navArgument("tripId") { type = NavType.LongType })
        ) { backStackEntry ->
            val tripId = backStackEntry.arguments?.getLong("tripId") ?: return@composable
            val viewModel: AddPlaceViewModel = viewModel(
                factory = travelPlannerViewModelFactory {
                    AddPlaceViewModel(
                        tripId = tripId,
                        repository = AppContainer.tripRepository
                    )
                }
            )
            val uiState by viewModel.uiState.collectAsState()

            LaunchedEffect(uiState.isSaved) {
                if (uiState.isSaved) {
                    navController.popBackStack()
                }
            }

            AddPlaceScreen(
                uiState = uiState,
                onBackClick = { navController.popBackStack() },
                onTitleChange = viewModel::onTitleChange,
                onDescriptionChange = viewModel::onDescriptionChange,
                onEstimatedCostChange = viewModel::onEstimatedCostChange,
                onCategorySelected = viewModel::onCategorySelected,
                onNoteChange = viewModel::onNoteChange,
                onSaveClick = viewModel::onSaveClick
            )
        }

        composable(
            route = Routes.BUDGET,
            arguments = listOf(navArgument("tripId") { type = NavType.LongType })
        ) { backStackEntry ->
            val tripId = backStackEntry.arguments?.getLong("tripId") ?: return@composable
            val viewModel: BudgetViewModel = viewModel(
                factory = travelPlannerViewModelFactory {
                    BudgetViewModel(
                        tripId = tripId,
                        repository = AppContainer.tripRepository
                    )
                }
            )
            val uiState by viewModel.uiState.collectAsState()

            BudgetScreen(
                uiState = uiState,
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(Routes.ABOUT) {
            AboutScreen(onBackClick = { navController.popBackStack() })
        }
    }
}
