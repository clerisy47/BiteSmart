package com.app.bitesmart.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.bitesmart.widgets.IngredientColumn
import com.app.bitesmart.widgets.TopAppBarWithTitle

@Composable
fun IngredientsScreen(modifier: Modifier = Modifier, navController: NavController) {

    Scaffold(
        modifier = modifier,
        topBar = { TopAppBarWithTitle(title = "Ingredients", navController = navController) }
    ) { innerPadding ->
        Surface(
            modifier = Modifier.padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                IngredientColumn(
                    modifier = Modifier.weight(3f)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    IconButton(
                        onClick = {
                            //Todo: add to history
                        },
                        modifier = Modifier.clip(shape = RoundedCornerShape(8.dp))
                            .background(color = Color(0xFF40C731))
                            .padding(horizontal = 10.dp, vertical = 8.dp)
                    ) {
                        Icon(
                            Icons.Default.Save,
                            contentDescription = "Add",
                            tint = Color.White,
                        )
                    }
                    IconButton(
                        onClick = {
                            //Todo: add to history

                        },
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(8.dp))
                            .background(color = Color(0xFFD94C4C))
                            .padding(horizontal = 10.dp, vertical = 8.dp)
                    ) {
                        Icon(
                            Icons.Outlined.Delete,
                            contentDescription = "Discard",
                            tint = Color.White
                        )
                    }

                }
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