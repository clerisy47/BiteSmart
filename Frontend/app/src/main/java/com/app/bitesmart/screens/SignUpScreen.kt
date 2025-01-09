package com.app.bitesmart.screens

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

@Composable
fun SignUpScreen(modifier: Modifier = Modifier,navController: NavController) {

    Scaffold(
        modifier = modifier,
        topBar = { TopAppBar(title = "Create Account") }
    ) { innerPadding ->
        Surface(
            modifier = Modifier.padding(innerPadding)
        ) {
            Text(
                text = "Sign up screen"
            )
        }

    }
}


@Preview
@Composable
fun SignUpScreenPreview() {
    val navController = rememberNavController()
    SignUpScreen(navController = navController)
}
