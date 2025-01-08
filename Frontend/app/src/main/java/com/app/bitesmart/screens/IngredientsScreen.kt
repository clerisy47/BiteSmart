package com.app.bitesmart.screens

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.app.bitesmart.R
import com.app.bitesmart.widgets.BottomAppBar
import com.app.bitesmart.widgets.TopAppBar
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.TextRecognizer
import com.google.mlkit.vision.text.latin.TextRecognizerOptions

@Composable
fun IngredientsScreen(modifier: Modifier = Modifier, bitmap: Bitmap ){
    val context = LocalContext.current
    val recognizer: TextRecognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)
    var recognizedText by remember { mutableStateOf("") }

    LaunchedEffect(bitmap) {
        val image = InputImage.fromBitmap(bitmap, 0)
        recognizer.process(image)
            .addOnSuccessListener { visionText ->
                recognizedText = visionText.text
            }
            .addOnFailureListener { e ->
                recognizedText = "Error: ${e.message}"
            }
    }
    Scaffold(
        modifier = modifier,
        topBar = { TopAppBar(title = "Ingredients") },
        bottomBar = { BottomAppBar() }
    ){innerPadding ->
        Surface(
            modifier = Modifier.padding(innerPadding)
        ){
            Column {
                Text(
                    text = recognizedText
                )
            }
        }


    }
}
@Preview
@Composable
fun IngredientsScreenPreview() {
     val context = LocalContext.current
    // Ensure the resource exists in your drawable folder (R.drawable.ingredients_img_sample)
    val bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.ingredients_img_sample)

    // Check if bitmap is valid before passing it to IngredientsScreen
    if (bitmap != null) {
        IngredientsScreen(bitmap = bitmap)
    } else {
        Text("Failed to load image")
    }
}