package com.app.bitesmart.screens


import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.bitesmart.R
import com.app.bitesmart.viewModels.UserViewModel
import com.app.bitesmart.widgets.BottomAppBar

@Composable
fun UserDashboardScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    userViewModel: UserViewModel
) {
    val username = remember { mutableStateOf("Loading...") }
    val allergies = remember { mutableStateOf<List<String>>(emptyList()) }
    LaunchedEffect(key1 = true) {
        username.value = userViewModel.getUsername()
        allergies.value = userViewModel.getAllergies()
    }
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    Scaffold(
        modifier = modifier,
        bottomBar = { BottomAppBar(navController = navController) }
    ) { innerPadding ->
        Surface(
            modifier = Modifier.padding(innerPadding)
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)
                        .background(color = MaterialTheme.colorScheme.secondaryContainer),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 32.dp)
                            .offset(y = 80.dp)
                            .background(color = MaterialTheme.colorScheme.onTertiary),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Image(
                                painter = painterResource(R.drawable.profile_img1),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(80.dp)
                                    .offset(y = (-44).dp)
                                    .clip(CircleShape),
                                contentScale = ContentScale.Crop

                            )
                            Column(
                                modifier = Modifier.offset(y = (-32).dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = username.value,
                                    style = MaterialTheme.typography.titleMedium,
                                )
                                Button(
                                    onClick = {
                                        Toast.makeText(
                                            context,
                                            "Navigates to Edit Profile....",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                ) {
                                    Text(
                                        text = "Edit Profile"
                                    )
                                }
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 16.dp),
                                    horizontalArrangement = Arrangement.SpaceEvenly,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Card(
                                        shape = MaterialTheme.shapes.small
                                    ) {
                                        Column(
                                            modifier = Modifier
                                                .padding(vertical = 8.dp)
                                                .width(98.dp),
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            verticalArrangement = Arrangement.spacedBy(8.dp)
                                        ) {
                                            Text(text = "Scans")
                                            Text(text = "30")
                                        }
                                    }
                                    Card(
                                        shape = MaterialTheme.shapes.small
                                    ) {
                                        Column(
                                            modifier = Modifier
                                                .padding(vertical = 8.dp)
                                                .width(98.dp),
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            verticalArrangement = Arrangement.spacedBy(8.dp)
                                        ) {
                                            Text(text = "Allergies")
                                            Text(text = "${allergies.value.size}")
                                        }
                                    }
                                }
                            }

                        }
                    }
                }

                // Other ui elements from here
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .padding(top = 60.dp)
                ) {
//                    Text("Allergies:", style = MaterialTheme.typography.titleMedium)
//                    allergies.value.forEach { allergy ->
//                        Text("• $allergy", style = MaterialTheme.typography.bodyMedium)
//                    }

                        Column(
                            modifier = Modifier.padding(16.dp)
                                .padding(top = 16.dp)
                        ) {
                            Text("Health Conditions", style = MaterialTheme.typography.titleLarge)

                            // Conditionally show "NONE" if the allergies list is empty
                            if (allergies.value.isEmpty()) {
                                Text("NONE", style = MaterialTheme.typography.bodyMedium)
                            } else {
                                // Horizontal scrollable row for allergies
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .horizontalScroll(rememberScrollState()),
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    // Create a card for each allergy
                                    allergies.value.forEach { allergy ->
                                        Card(
                                            modifier = Modifier
                                                .height(50.dp)
                                                .padding(4.dp),
                                            shape = RoundedCornerShape(16.dp),
                                            elevation = CardDefaults.cardElevation(4.dp),
                                            colors = CardDefaults.elevatedCardColors()
                                        ) {
                                            Box(
                                                modifier = Modifier
                                                    .fillMaxSize()
                                                    .padding(8.dp),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Text(
                                                    text = allergy,
                                                    style = MaterialTheme.typography.bodyMedium
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        HealthScoreSection()

            }
        }
    }
}
    }
@Composable
fun HealthScoreSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Heading
        Text(
            text = "Health Score",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Horizontal scrollable Row for score cards
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp), // Space between cards
            modifier = Modifier.horizontalScroll(rememberScrollState()) // Enable horizontal scrolling
        ) {
            // Nutrition Score Card
            ScoreCard(
                score = "Nutrition Score",
                progress = 0.85f, // 85% progress
                color = Color(0xFF388E3C) // Green for Nutrition Score
            )

            // Cancer Score Card
            ScoreCard(
                score = "Cancer Score",
                progress = 0.7f, // 70% progress
                color = Color(0xFFB71C1C) // Red for Cancer Score
            )

            // Eco Score Card
            ScoreCard(
                score = "Eco Score",
                progress = 0.65f, // 65% progress
                color = Color(0xFF1976D2) // Blue for Eco Score
            )
        }
    }
}

@Composable
fun ScoreCard(score: String, progress: Float, color: Color) {
    Card(
        modifier = Modifier
            .size(200.dp, 200.dp), // Size of each score card
        colors = CardDefaults.elevatedCardColors(),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = score,
                style = MaterialTheme.typography.titleMedium,
                color = color
            )

            Spacer(modifier = Modifier.height(16.dp))

            CircularProgressIndicator(
                progress = progress,
                modifier = Modifier.size(80.dp),
                color = color,
                strokeWidth = 8.dp
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "${(progress * 100).toInt()}%", // Display score as percentage
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Preview
@Composable
fun UserDashboardScreenPreview() {
    val navController = rememberNavController()
    val userViewModel = UserViewModel(LocalContext.current) // Pass your ViewModel here
    UserDashboardScreen(navController = navController, userViewModel = userViewModel)
}
