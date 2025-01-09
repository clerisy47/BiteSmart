package com.app.bitesmart.domain

import android.graphics.Bitmap
import android.util.Log
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.TextRecognizer
import com.google.mlkit.vision.text.latin.TextRecognizerOptions

class TextRecognitionUseCase{
    companion object{
        fun execute(bitmap: Bitmap){

            val recognizer: TextRecognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)

            // Create InputImage from the Bitmap
            val image = InputImage.fromBitmap(bitmap, 0)

            // Process the image and extract text
            recognizer.process(image)
                .addOnSuccessListener { visionText ->
                    // Text recognized successfully, now you can use it
                    val recognizedText = visionText.text
                    Log.d("RecognizedText", "Recognized Text: $recognizedText")
                }
                .addOnFailureListener { e ->
                    Log.d("RecognizedText", "Error recognizing text: ${e.message}")
                }
        }
    }
}