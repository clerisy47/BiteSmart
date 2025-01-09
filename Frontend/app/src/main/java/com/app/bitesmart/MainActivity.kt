package com.app.bitesmart

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.app.bitesmart.navigation.BiteSmartNavigation
import com.app.bitesmart.ui.theme.BiteSmartTheme
class MainActivity : ComponentActivity() {

    private val cameraPermissionRequest =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                // Implement camera related  code
                launchApp()
            } else {
                // Camera permission denied
            }

        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Check if the camera permission is granted
        when (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)) {
            PackageManager.PERMISSION_GRANTED -> {
                // Camera permission already granted
                // Implement camera-related code here
                launchApp()
            }
            else -> {
                // Camera permission not granted, request it
                cameraPermissionRequest.launch(Manifest.permission.CAMERA)
            }
        }

        enableEdgeToEdge()
    }
    private fun launchApp(){
        setContent {
            BiteSmartTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
                    //don't include innerPadding it disturbs the padding of topAppBar and ButtonAppBar
                    BiteSmartNavigation()
                }
            }
        }
    }

}


@Composable
fun Greeting(){
    Text(
        text = "Hello"
    )
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BiteSmartTheme {
        Greeting()
    }
}