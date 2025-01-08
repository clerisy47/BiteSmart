package com.app.bitesmart.screens


import android.content.Context
import androidx.camera.core.CameraSelector
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
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.PhotoCamera
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.app.bitesmart.widgets.BottomAppBar
import com.app.bitesmart.widgets.TopAppBar
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@Composable
fun FoodScanScreen(modifier: Modifier = Modifier) {
    val lensFacing = CameraSelector.LENS_FACING_BACK
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current
    val preview = androidx.camera.core.Preview.Builder().build()
    val previewView = remember {
        PreviewView(context)
    }
    // To use back camera only
    val cameraxSelector = CameraSelector.Builder().requireLensFacing(lensFacing).build()
    LaunchedEffect(lensFacing) {
        //provides access to camera
        val cameraProvider = context.getCameraProvider()
        cameraProvider.unbindAll()
        //binds camera lifecycle to this composable
        cameraProvider.bindToLifecycle(lifecycleOwner, cameraxSelector, preview)
        //allows camera preview to be displayed on screen
        preview.surfaceProvider = previewView.surfaceProvider

    }
    Scaffold(
        modifier = modifier,
        topBar = { TopAppBar() },
        bottomBar = { BottomAppBar() }
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
                    IconButton(
                        onClick = {
                            //Todo: capture image
                        },
                        modifier = Modifier
                            .size(size = 60.dp)
                            .clip(shape = CircleShape)
                            .background(color = MaterialTheme.colorScheme.primaryContainer)
                    ) {
                        Icon(
                            Icons.Default.Close,
                            contentDescription = "Camera Image",
                            modifier = Modifier.size(size = 40.dp)
                        )
                    }
                    IconButton(
                        onClick = {
                            //Todo: capture image
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
                    IconButton(
                        onClick = {
                            //Todo: capture image
                        },
                        modifier = Modifier
                            .size(size = 60.dp)
                            .clip(shape = CircleShape)
                            .background(color = MaterialTheme.colorScheme.primaryContainer)
                    ) {
                        Icon(
                            Icons.Default.Check,
                            contentDescription = "Camera Image",
                            modifier = Modifier.size(size = 40.dp)
                        )
                    }
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
    FoodScanScreen()
}
