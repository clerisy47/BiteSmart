package com.app.bitesmart.domain

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Log
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.core.content.ContextCompat
import com.app.bitesmart.viewModels.ImageCaptureViewModel
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CaptureImageUseCase {
    companion object{
        fun captureImage(imageCapture: ImageCapture, context: Context, viewModel: ImageCaptureViewModel) {
            // Generate unique filename based on timestamp to avoid overwriting
            val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
            val fileName = "F_img$timeStamp.jpg"

            val outputOptions = ImageCapture.OutputFileOptions.Builder(
                File(context.cacheDir, fileName)
            ).build()

            imageCapture.takePicture(
                outputOptions,
                ContextCompat.getMainExecutor(context),
                object : ImageCapture.OnImageSavedCallback {
                    override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                        val bitmap = BitmapFactory.decodeFile(outputFileResults.savedUri?.path)
                        if (bitmap != null) {
                            viewModel.addImage(bitmap) // Add the image to the ViewModel's list
                        }
                    }

                    override fun onError(exception: ImageCaptureException) {
                        Log.d("CaptureImage", "Error capturing image: $exception")
                    }
                }
            )
        }
    }
}
