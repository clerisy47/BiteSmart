package com.app.bitesmart.widgets

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.bitesmart.data.dummyData.IngredientsData
import com.app.bitesmart.data.dummyData.getIngredientsData


@Composable
fun IngredientColumn(
    modifier: Modifier = Modifier,
    ingredientList: List<IngredientsData> = getIngredientsData(),
    userAllergies: List<String> = emptyList()
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(ingredientList) { ingredient ->
            var isExpanded by remember { mutableStateOf(false) }
            val hasAllergy = ingredient.allergies.any { it in userAllergies }
            var allergies = ingredient.allergies
            Surface(
                modifier = Modifier.padding(top = 8.dp)
                    .animateContentSize (),
                shape = MaterialTheme.shapes.small,
                color = MaterialTheme.colorScheme.primaryContainer
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = ingredient.name,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ){
                            Icon(
                                imageVector = Icons.Filled.Circle,
                                contentDescription = "indicator",
                                tint = if (hasAllergy) Color(0xFFD94C4C) else MaterialTheme.colorScheme.primaryContainer
                            )
                            IconButton(
                                onClick = {
                                    isExpanded = !isExpanded
                                    Log.i("IngredientClicked", ingredient.name)
                                },
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .background(Color.White)
                            ) {
                                Icon(
                                    imageVector = if(isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                                    contentDescription = "Arrow Down button"
                                )

                            }
                        }

                    }
                    if (isExpanded) {
                    HorizontalDivider(modifier = Modifier.padding(4.dp))
                        Column {
                            Text(
                                text = "Description:",
                                style = MaterialTheme.typography.labelLarge,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                            Text(
                                text = ingredient.description,
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Text(
                                text = "Source:",
                                style = MaterialTheme.typography.labelLarge,
                                modifier = Modifier.padding(top = 2.dp)
                            )
                            Text(
                                text = ingredient.source,
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Text(
                                text = "Allergies:",
                                style = MaterialTheme.typography.labelLarge,
                                modifier = Modifier.padding(top = 2.dp)
                            )
                            allergies.forEach { allergy ->
                                Text("• $allergy", style = MaterialTheme.typography.bodyMedium)
                            }
                        }


                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewIngredientColumn() {
    IngredientColumn()
}
