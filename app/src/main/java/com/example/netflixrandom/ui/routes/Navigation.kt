package com.example.netflixrandom.ui.routes

import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.netflixrandom.ui.screens.HomeScreen.Screen.HomeScreen
import com.example.netflixrandom.ui.screens.HomeScreen.ViewModel.HomeViewModel
import com.example.netflixrandom.ui.screens.LastChoices.Screen.LastChoicesScreen

@Composable
fun Navigation(
    navController: NavHostController = rememberNavController(),
) {
    val homeViewModel: HomeViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Routes.HomeScreen.route
    ) {
        composable(Routes.HomeScreen.route) {
            HomeScreen(homeViewModel)
        }

        composable(Routes.LastChoicesScreen.route) {
            LastChoicesScreen(viewModel = homeViewModel)
        }
    }
}