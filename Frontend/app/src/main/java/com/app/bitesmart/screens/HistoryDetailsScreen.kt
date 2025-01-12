package com.app.bitesmart.screens


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.bitesmart.widgets.IngredientColumn
import com.app.bitesmart.widgets.TopAppBarWithTitle

@Composable
fun HistoryDetailsScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {

    Scaffold(
        modifier = modifier,
        topBar = { TopAppBarWithTitle(title = "History Details", navController = navController) }
    ) { innerPadding ->
        Surface(
            modifier = Modifier.padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    IngredientColumn(
                        modifier = Modifier.weight(3f),
                    )
                    Spacer(Modifier.size(8.dp))
                    Text(
                        text = "Additional Information:",
                        style = MaterialTheme.typography.labelLarge
                    )
                    Text(
                        text = "No additional information",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }


    }
}

@Preview
@Composable
fun HistoryScreenDetailsPreview() {
    val navController = rememberNavController()
    HistoryDetailsScreen(navController = navController)
}
