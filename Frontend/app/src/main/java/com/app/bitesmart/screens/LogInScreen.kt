package com.app.bitesmart.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.bitesmart.R
import com.app.bitesmart.components.CustomTextField
import com.app.bitesmart.navigation.NavigationScreens
import com.app.bitesmart.viewModels.UserViewModel
import com.app.bitesmart.viewModels.UserViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun LogInScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    // States for text fields
    val username = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    val userViewModel: UserViewModel = viewModel(
        factory = UserViewModelFactory(context = navController.context)
    )

    Surface(
        modifier = modifier
    ) {
        Column {
            Image(
                painter = painterResource(R.drawable.log_in_img_banner),
                contentDescription = null
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                Text(
                    text = stringResource(R.string.welcome_to_bitesmart),
                    style = MaterialTheme.typography.titleLarge
                )

                Image(
                    painter = painterResource(R.drawable.log_in_img_avatar),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(16.dp)
                        .size(size = 80.dp)
                        .clip(shape = CircleShape)

                )

                Column(
                    verticalArrangement = Arrangement.spacedBy(space = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {

                    CustomTextField(label = stringResource(R.string.username), textState = username)
                    CustomTextField(label = stringResource(R.string.password), textState = password, isLast = true, isPassword = true)

                    Text(
                        text = stringResource(R.string.forget_password),
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier
                            .align(alignment = Alignment.End)
                            .clickable {
                                // Todo: provide a way to get the password
                            }
                    )
                    Button(
                        onClick = {
                            // Access the values of username and password for verification
                            val enteredUsername = username.value
                            val enteredPassword = password.value
                            //Todo: go to user dashboard after verifying
//
                            kotlinx.coroutines.GlobalScope.launch {
                                try {
                                    val (storedUsername, storedPassword) = userViewModel.getAccountDetails()

                                    // Check the credentials
                                    if (enteredUsername == storedUsername && enteredPassword == storedPassword) {
                                        // Navigate to the user dashboard if credentials are correct
                                        withContext(Dispatchers.Main) {
                                            navController.navigate(route = NavigationScreens.UserDashboardScreen.name)
                                            Toast.makeText(
                                                navController.context,
                                                "Log In Successfully",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    } else {
                                        // Show error message if credentials don't match
                                        withContext(Dispatchers.Main) {
                                            Toast.makeText(
                                                navController.context,
                                                "Invalid username or password",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }
                                } catch (e: Exception) {
                                    Log.e("DataStore", "Error occurred while checking credentials: ${e.message}", e)
                                    // Handle exceptions if needed
                                    withContext(Dispatchers.Main) {
                                        Toast.makeText(
                                            navController.context,
                                            "Error occurred while checking credentials",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                            }
                        }
                    ) {
                        Text(
                            text = stringResource(R.string.log_in)
                        )
                    }
                }

                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
                    HorizontalDivider(
                        modifier = Modifier.weight(2f)
                    )
                    Text(
                        text = stringResource(R.string.or),
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    HorizontalDivider(
                        modifier = Modifier.weight(2f)
                    )
                }
                Button(
                    onClick = {
                        //Todo: go to the sign up page to sign in
                        navController.navigate(route = NavigationScreens.SignUpScreen.name)
                    },
                ) {
                    Text(
                        text = stringResource(R.string.sign_up)
                    )
                }

            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun LogInScreenPreview() {
    val navController = rememberNavController()
    LogInScreen(navController = navController)
}