package com.app.bitesmart.screens


import android.content.Context
import android.util.Log
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PhotoCamera
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.bitesmart.domain.CaptureImageUseCase
import com.app.bitesmart.domain.TextRecognitionUseCase
import com.app.bitesmart.navigation.NavigationScreens
import com.app.bitesmart.viewModels.ImageCaptureViewModel
import com.app.bitesmart.widgets.BottomAppBar
import com.app.bitesmart.widgets.TopAppBar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
@Composable
fun FoodScanScreen(modifier: Modifier = Modifier, viewModel: ImageCaptureViewModel, navController: NavController) {
    val lensFacing = CameraSelector.LENS_FACING_BACK
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current
    val preview = androidx.camera.core.Preview.Builder().build()
    val previewView = remember {
        PreviewView(context)
    }
    // To use back camera only
    val cameraxSelector = CameraSelector.Builder().requireLensFacing(lensFacing).build()
    val imageCapture = remember {
        ImageCapture.Builder().build()
    }
    LaunchedEffect(lensFacing) {
        //provides access to camera
        val cameraProvider = context.getCameraProvider()
        cameraProvider.unbindAll()
        //binds camera lifecycle to this composable
        cameraProvider.bindToLifecycle(lifecycleOwner, cameraxSelector, preview, imageCapture)
        //allows camera preview to be displayed on screen
        preview.surfaceProvider = previewView.surfaceProvider

    }
    val scope = rememberCoroutineScope()
    Scaffold(
        modifier = modifier,
        topBar = { TopAppBar(title = "Food Scan") },
        bottomBar = { BottomAppBar(navController = navController) }
    ) { innerPadding ->
        Surface(
            modifier = Modifier.padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 24.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(space = 32.dp)
            ) {
//                Image(
//                    painter = painterResource(R.drawable.log_in_img_avatar),
//                    contentDescription = "Image that needs to be scanned",
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .weight(2f)
//                )

                //To show live preview of camera
                AndroidView(
                    factory = { previewView },
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(2f)
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
//                    IconButton(
//                        onClick = {
//                            captureImage(imageCapture, context, viewModel)
//                        },
//                        modifier = Modifier
//                            .size(size = 60.dp)
//                            .clip(shape = CircleShape)
//                            .background(color = MaterialTheme.colorScheme.primaryContainer)
//                    ) {
//                        Icon(
//                            Icons.Default.Close,
//                            contentDescription = "Camera Image",
//                            modifier = Modifier.size(size = 40.dp)
//                        )
//                    }
                    IconButton(
                        onClick = {
                            //Todo: capture image, recognize text and navigate
                            // Add a delay before starting text recognition to ensure the image is saved
                            // Capture the image
                            CaptureImageUseCase.captureImage(imageCapture, context, viewModel)

                            scope.launch {
                                // Introducing a delay (e.g., 2 seconds) before continuing
                                delay(2000) // Delay for 2 seconds

                                // Ensure that the image is available before executing text recognition
                                val latestImage = viewModel.getLatestImage()
                                if (latestImage != null) {
                                    TextRecognitionUseCase.execute(latestImage)
                                } else {
                                    Log.d("CaptureImage", "Image not ready yet")
                                }

                                // Navigate to the Ingredients screen after text recognition
                                navController.navigate(route = NavigationScreens.IngredientsScreen.name)
                            }
                        },
                        modifier = Modifier
                            .size(size = 80.dp)
                            .clip(shape = CircleShape)
                            .background(color = MaterialTheme.colorScheme.primaryContainer)
                    ) {
                        Icon(
                            Icons.Outlined.PhotoCamera,
                            contentDescription = "Camera Image",
                            modifier = Modifier.size(size = 40.dp)
                        )
                    }
//                    IconButton(
//                        onClick = {
//                            //Todo: capture image
//                        },
//                        modifier = Modifier
//                            .size(size = 60.dp)
//                            .clip(shape = CircleShape)
//                            .background(color = MaterialTheme.colorScheme.primaryContainer)
//                    ) {
//                        Icon(
//                            Icons.Default.Check,
//                            contentDescription = "Camera Image",
//                            modifier = Modifier.size(size = 40.dp)
//                        )
//                    }
                }

            }
        }

    }
}

private suspend fun Context.getCameraProvider(): ProcessCameraProvider =
    suspendCoroutine { continuation ->
        ProcessCameraProvider.getInstance(this).also { cameraProvider ->
            cameraProvider.addListener({
                continuation.resume(cameraProvider.get())
            }, ContextCompat.getMainExecutor(this))
        }
    }



@Preview
@Composable
fun FoodScanScreenPreview() {
    val navController = rememberNavController()
    FoodScanScreen(viewModel = ImageCaptureViewModel(), navController = navController)
}
