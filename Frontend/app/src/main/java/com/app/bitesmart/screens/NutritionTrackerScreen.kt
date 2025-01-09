package com.app.bitesmart.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.bitesmart.widgets.BottomAppBar
import com.app.bitesmart.widgets.TopAppBar

@Composable
fun NutritionTrackerScreen(modifier: Modifier = Modifier, navController: NavController) {

    Scaffold(
        modifier = modifier,
        topBar = { TopAppBar(title = "Nutrition Tracker", navController = navController) },
        bottomBar = { BottomAppBar(navController = navController) }
    ) { innerPadding ->
        Surface(
            modifier = Modifier.padding(innerPadding)
        ) {
            Text(
                text = "Nutrition Tracker screen"
            )
        }

    }
}


@Preview
@Composable
fun NutritionTrackerScreenPreview() {
    val navController = rememberNavController()
    NutritionTrackerScreen(navController = navController)
}
