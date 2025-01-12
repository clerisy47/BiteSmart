package com.app.bitesmart.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.bitesmart.widgets.BottomAppBar
import com.app.bitesmart.widgets.HistoryColumn
import com.app.bitesmart.widgets.TopAppBar

@Composable
fun HistoryScreen(modifier: Modifier = Modifier, navController: NavController) {

    Scaffold(
        modifier = modifier,
        topBar = { TopAppBar(title = "History") },
        bottomBar = { BottomAppBar(navController = navController) }
    ) { innerPadding ->
        Surface(
            modifier = Modifier.padding(innerPadding)
        ) {
            Column (
                modifier = Modifier.padding(16.dp)
            ){
                HistoryColumn(navController = navController)
            }
        }

    }
}


@Preview
@Composable
fun HistoryScreenPreview() {
    val navController = rememberNavController()
    HistoryScreen(navController = navController)
}
