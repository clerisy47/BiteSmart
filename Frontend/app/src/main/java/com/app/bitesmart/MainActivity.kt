package com.app.bitesmart

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.app.bitesmart.navigation.BiteSmartNavigation
import com.app.bitesmart.onBoard.OnBoardingScreen
import com.app.bitesmart.onBoard.OnBoardingUtils
import com.app.bitesmart.permission.PermissionDeniedScreen
import com.app.bitesmart.permission.PermissionRationaleScreen
import com.app.bitesmart.ui.theme.BiteSmartTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val onBoardingUtils by lazy { OnBoardingUtils(this) }
    private val cameraPermissionRequest =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                launchApp()
            } else {
                showPermissionDeniedMessage()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        checkCameraPermission()

        enableEdgeToEdge()
    }

    private fun checkCameraPermission() {
        when (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)) {
            PackageManager.PERMISSION_GRANTED -> {
                launchApp()
            }
            PackageManager.PERMISSION_DENIED -> {
                if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                    showPermissionRationale()
                } else {
                    cameraPermissionRequest.launch(Manifest.permission.CAMERA)
                }
            }
        }
    }

    private fun launchApp() {
        setContent {
            BiteSmartTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
                    if (onBoardingUtils.isOnBoardingCompleted()) {
                        BiteSmartNavigation()
                    } else {
                        ShowOnBoardingScreen()
                    }
                }
            }
        }
    }

    private fun showPermissionRationale() {
        setContent {
            PermissionRationaleScreen(
                onRequestPermission = { openAskPermission() },
            )
        }
    }

    private fun showPermissionDeniedMessage() {
        setContent {
            PermissionDeniedScreen(onOpenSettings = { openAskPermission() })
        }
    }

    private fun openAskPermission() {
        cameraPermissionRequest.launch(Manifest.permission.CAMERA)
    }

    @Composable
    private fun ShowOnBoardingScreen() {
        val scope = rememberCoroutineScope()
        Box(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)) {
            OnBoardingScreen {
                onBoardingUtils.setOnBoardingCompleted()
                scope.launch {
                    setContent {
                        BiteSmartNavigation()
                    }
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        BiteSmartTheme {
            ShowOnBoardingScreen()
        }
    }
}
