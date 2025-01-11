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
import com.app.bitesmart.ui.theme.BiteSmartTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val onBoardingUtils by lazy { OnBoardingUtils(this) }
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

        installSplashScreen()

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
                    if(onBoardingUtils.isOnBoardingCompleted()){
                        BiteSmartNavigation()
                    }else{
                        ShowOnBoardingScreen()
                    }
                }
            }
        }
    }
    @Composable
    private fun ShowOnBoardingScreen(){
        val scope = rememberCoroutineScope()
        Box(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)){
            OnBoardingScreen {
                onBoardingUtils.setOnBoardingCompleted()
//            Toast.makeText(context,"OnBoarding Completed", Toast.LENGTH_SHORT).show()
                scope.launch {
                    setContent{
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





