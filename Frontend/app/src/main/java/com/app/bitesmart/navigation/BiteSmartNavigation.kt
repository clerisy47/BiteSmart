package com.app.bitesmart.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.bitesmart.screens.FoodScanScreen
import com.app.bitesmart.screens.HistoryScreen
import com.app.bitesmart.screens.IngredientsScreen
import com.app.bitesmart.screens.LogInScreen
import com.app.bitesmart.screens.SignUpScreen
import com.app.bitesmart.screens.UserDashboardScreen
import com.app.bitesmart.viewModels.ImageViewModel

@Composable
fun BiteSmartNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController, startDestination = NavigationScreens.LogInScreen.name
    ) {
        composable(route = NavigationScreens.LogInScreen.name) {
            LogInScreen(navController = navController)
        }
        composable(route = NavigationScreens.SignUpScreen.name) {
            SignUpScreen(navController = navController)
        }
        composable(route = NavigationScreens.UserDashboardScreen.name) {
            UserDashboardScreen(navController = navController)
        }
        composable(route = NavigationScreens.HistoryScreen.name) {
            HistoryScreen(navController = navController)
        }
        composable(route = NavigationScreens.IngredientsScreen.name) {
            IngredientsScreen(navController = navController)
        }
        composable(route = NavigationScreens.FoodScanScreen.name) {
            // Pass the ViewModel to the FoodScanScreen
            val viewModel: ImageViewModel = remember { ImageViewModel() }
            FoodScanScreen(navController = navController, viewModel = viewModel)
        }
    }
}