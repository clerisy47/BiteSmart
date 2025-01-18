package com.app.bitesmart.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.bitesmart.R
import com.app.bitesmart.components.CustomTextField
import com.app.bitesmart.navigation.NavigationScreens
import com.app.bitesmart.widgets.TopAppBar
import com.app.bitesmart.viewModels.UserViewModel
import com.app.bitesmart.viewModels.UserViewModelFactory

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier, navController: NavController,
    context: Context = LocalContext.current
) {

    val name = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val phoneNumber = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }

    // Get ViewModel
    val userViewModel: UserViewModel = viewModel(
        factory = UserViewModelFactory(context = navController.context)
    )


    Scaffold(
        modifier = modifier,
        topBar = { TopAppBar(title = "Create Account") }
    ) { innerPadding ->
        Surface(
            modifier = Modifier.padding(innerPadding)
        ) {
            Column(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CustomTextField(label = "Name", textState = name)
                CustomTextField(label = "Email", textState = email)
                CustomTextField(label = "Phone Number", textState = phoneNumber)
                CustomTextField(label = "Password", textState = password)
                CustomTextField(label = "Confirm Password", textState = confirmPassword, isLast = true, isPassword = true)
                Spacer(Modifier.width(8.dp))
                Button(
                    onClick = {
                        // Save data to DataStore when the user clicks sign-up
                        if (password.value == confirmPassword.value) {
                            userViewModel.saveAccountDetails(
                                name.value,
                                email.value,
                                phoneNumber.value,
                                password.value,
                                confirmPassword.value
                            )
                            // Navigate to login or another screen after saving
                            navController.navigate(route = NavigationScreens.LogInScreen.name)
                            Toast.makeText(context, "Sign up successful", Toast.LENGTH_SHORT).show()

                        } else {
                            // Show error message (confirm password mismatch)
                            Toast.makeText(context, "Confirm Password doesn't matches the password", Toast.LENGTH_SHORT).show()
                        }
                    }
                ) {
                    Text(text = stringResource(R.string.sign_up))
                }
                Row {
                    Text(text = "Already have an account? ")
                    Text(
                        text = stringResource(R.string.log_in),
                        modifier = Modifier
                            .clickable {
                                navController.navigate(route = NavigationScreens.LogInScreen.name)
                            },
                        style = androidx.compose.ui.text.TextStyle(
                            textDecoration = TextDecoration.Underline,
                        )
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun SignUpScreenPreview() {
    val navController = rememberNavController()
    SignUpScreen(navController = navController, context = LocalContext.current)
}
