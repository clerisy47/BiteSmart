package com.app.bitesmart.domain

import android.graphics.Bitmap
import android.util.Log
import com.app.bitesmart.viewModels.TextRecognizedViewModel
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.TextRecognizer
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

class TextRecognitionUseCase {
    companion object {
        fun execute(bitmap: Bitmap, viewModel: TextRecognizedViewModel) {
            val recognizer: TextRecognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)

            // Create InputImage from the Bitmap
            val image = InputImage.fromBitmap(bitmap, 0)

            // Process the image and extract text
            recognizer.process(image)
                .addOnSuccessListener { visionText ->
                    // Text recognized successfully, now you can use it
                    val recognizedText = visionText.text
                    Log.d("RecognizedText", "Recognized Text: $recognizedText")

                    // Send POST request with recognized text
                    sendPostRequest(recognizedText, viewModel)
                }
                .addOnFailureListener { e ->
                    Log.d("RecognizedText", "Error recognizing text: ${e.message}")
                }
        }

        private fun sendPostRequest(recognizedText: String, viewModel: TextRecognizedViewModel) {
            val client = OkHttpClient.Builder()
                .build()

            val json = JSONObject().apply {
                put("details", recognizedText)
            }
            val mediaType = "application/json; charset=utf-8".toMediaTypeOrNull()
            val body = RequestBody.create(mediaType, json.toString())

            val request = Request.Builder()
                .url("https://bitesmart-vf7d.onrender.com/api/get-information/")
                .post(body)
                .build()

            client.newCall(request).enqueue(object : okhttp3.Callback {
                override fun onFailure(call: okhttp3.Call, e: IOException) {
                    Log.d("PostRequest", "Failed to send request: ${e.message}")
                }

                override fun onResponse(call: okhttp3.Call, response: Response) {
                    response.use {
                        val responseCode = response.code
                        val responseBody = response.body?.string()
                        Log.d("PostRequest", "Response Code: $responseCode")
                        Log.d("PostRequest", "Response Body: $responseBody")

                        if (!response.isSuccessful) {
                            Log.d("PostRequest", "Unexpected code $responseCode")
                        } else {
                            responseBody?.let {
                                viewModel.updateResponse(responseBody, true)
                            }
                        }
//                        viewModel.updateResponse("Response from server", true)
                    }
                }

            })
        }

    }
}

