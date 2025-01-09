package com.app.bitesmart.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.bitesmart.R
import com.app.bitesmart.widgets.BottomAppBar

@Composable
fun UserDashboardScreen(modifier: Modifier = Modifier, navController: NavController) {
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
                                .offset(y = 60.dp)
                                .background(color = MaterialTheme.colorScheme.onTertiary),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Image(
                                    painter = painterResource(R.drawable.log_in_img_avatar),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(80.dp)
                                        .offset(y = (-44).dp)
                                        .clip(CircleShape)
                                )
                                Column(
                                    modifier = Modifier.offset(y = (-32).dp),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ){
                                    Text(
                                        text = "Angela Marksmen",
                                        style = MaterialTheme.typography.titleMedium,
                                    )
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
                                                modifier = Modifier.padding(vertical = 16.dp).width(98.dp),
                                                horizontalAlignment = Alignment.CenterHorizontally,
                                                verticalArrangement = Arrangement.spacedBy(8.dp)
                                            ) {
                                                Text(text = "Scans")
                                                Text(text = "80")
                                            }
                                        }
                                        Card(
                                            shape = MaterialTheme.shapes.small
                                        ) {
                                            Column(
                                                modifier = Modifier.padding(vertical = 16.dp).width(98.dp),
                                                horizontalAlignment = Alignment.CenterHorizontally,
                                                verticalArrangement = Arrangement.spacedBy(8.dp)
                                            ) {
                                                Text(text = "Allergies")
                                                Text(text = "10")
                                            }
                                        }
                                    }
                                }

                            }
                        }
                }

                // Other ui elements from here
                Column(
                    modifier = Modifier.fillMaxSize().padding(top = 60.dp).padding(16.dp)
                ) {
                    Text(
                        text = "Other ui elements"
                    )
                }

            }
        }
    }
}

@Preview
@Composable
fun UserDashboardScreenPreview() {
    val navController = rememberNavController()
    UserDashboardScreen(navController = navController)
}
