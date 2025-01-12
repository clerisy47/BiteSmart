package com.app.bitesmart.screens.allergies

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.bitesmart.widgets.TopAppBar

import com.app.bitesmart.data.userData.HealthConditions

@Composable
fun AllergiesScreen(
    onSave: (HealthConditions) -> Unit
) {
    var newAllergy by remember { mutableStateOf(TextFieldValue("")) }
    var newCondition by remember { mutableStateOf(TextFieldValue("")) }
    var allergies by remember { mutableStateOf(mutableListOf<String>()) }
    var conditions by remember { mutableStateOf(mutableListOf<String>()) }

    Scaffold(
        topBar = {
            TopAppBar(title = "Health Conditions")
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                Text("Add Allergies", style = MaterialTheme.typography.titleMedium)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BasicTextField(
                        value = newAllergy,
                        onValueChange = { newAllergy = it },
                        modifier = Modifier
                            .weight(1f)
                            .background(MaterialTheme.colorScheme.onPrimary, MaterialTheme.shapes.small)
                            .padding(8.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = {
                        if (newAllergy.text.isNotBlank()) {
                            allergies.add(newAllergy.text)
                            newAllergy = TextFieldValue("") // Clear input
                        }
                    }) {
                        Text("Add")
                    }
                }

                Text("Allergies List:", style = MaterialTheme.typography.titleMedium)
                allergies.forEach { allergy ->
                    Text("- $allergy", style = MaterialTheme.typography.bodyMedium)
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text("Add Conditions (e.g., Diabetes)", style = MaterialTheme.typography.titleMedium)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BasicTextField(
                        value = newCondition,
                        onValueChange = { newCondition = it },
                        modifier = Modifier
                            .weight(1f)
                            .background(MaterialTheme.colorScheme.onPrimary, MaterialTheme.shapes.small)
                            .padding(8.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = {
                        if (newCondition.text.isNotBlank()) {
                            conditions.add(newCondition.text)
                            newCondition = TextFieldValue("") // Clear input
                        }
                    }) {
                        Text("Add")
                    }
                }

                Text("Conditions List:", style = MaterialTheme.typography.titleMedium)
                conditions.forEach { condition ->
                    Text("- $condition", style = MaterialTheme.typography.bodyMedium)
                }

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        val healthConditions = HealthConditions(
                            allergiesName = allergies,
                            conditionsName = conditions
                        )
                        onSave(healthConditions)
                    },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("Save")
                }
            }
        }
    )
}
@Preview(
    showBackground = true,
)
@Composable
fun CustomAllergiesScreenPreview() {
    AllergiesScreen(
        onSave = { healthConditions ->
            // Simulate a save action

        }
    )
}


