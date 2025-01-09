package com.app.bitesmart.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.bitesmart.widgets.TopAppBar
import com.app.bitesmart.widgets.TopAppBarWithTitle

@Composable
fun IngredientsScreen(modifier: Modifier = Modifier, navController: NavController ){

    Scaffold(
        modifier = modifier,
        topBar = { TopAppBarWithTitle(title = "Ingredients", navController = navController) }
    ){innerPadding ->
        Surface(
            modifier = Modifier.padding(innerPadding)
        ){
            Column {
                Text(
                    text = "Ingredient Screen"
                )
            }
        }


    }
}
@Preview
@Composable
fun IngredientsScreenPreview() {
    val navController = rememberNavController()
    IngredientsScreen(navController = navController)
}