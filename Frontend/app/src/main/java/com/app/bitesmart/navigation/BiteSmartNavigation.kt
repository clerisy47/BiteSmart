package com.app.bitesmart.navigation

import AllergiesScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.app.bitesmart.screens.FoodScanScreen
import com.app.bitesmart.screens.HistoryScreen
import com.app.bitesmart.screens.HistoryDetailsScreen
import com.app.bitesmart.screens.IngredientsScreen
import com.app.bitesmart.screens.LogInScreen
import com.app.bitesmart.screens.SignUpScreen
import com.app.bitesmart.screens.UserDashboardScreen
import com.app.bitesmart.viewModels.UserViewModel
import com.app.bitesmart.viewModels.UserViewModelFactory
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@Composable
fun BiteSmartNavigation() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val userViewModel: UserViewModel = viewModel(factory = UserViewModelFactory(context))

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
            UserDashboardScreen(navController = navController, userViewModel = userViewModel)
        }
        composable(route = NavigationScreens.HistoryScreen.name) {
            HistoryScreen(navController = navController)
        }
        composable(route = NavigationScreens.FoodScanScreen.name) {
            FoodScanScreen(navController = navController)
        }
        composable(
            route = "${NavigationScreens.IngredientsScreen.name}/{responseText}",
            arguments = listOf(navArgument("responseText") { type = NavType.StringType })
        ) { backStackEntry ->
            val encodedResponseText = backStackEntry.arguments?.getString("responseText") ?: ""
            val responseText = URLDecoder.decode(encodedResponseText, StandardCharsets.UTF_8.toString())
            IngredientsScreen(navController = navController, responseText = responseText, userViewModel = userViewModel)
        }
        composable(route = NavigationScreens.HistoryDetailsScreen.name) {
            HistoryDetailsScreen(navController = navController)
        }
        composable(route = NavigationScreens.AllergiesScreen.name) {
            AllergiesScreen(navController = navController)
        }
    }
}
