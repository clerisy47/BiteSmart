package com.app.bitesmart.viewModels

import android.graphics.Bitmap
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ImageViewModel : ViewModel() {
    private val _images = mutableListOf<Bitmap>()
    val images: List<Bitmap> get() = _images

    fun addImage(bitmap: Bitmap) {
        _images.add(bitmap)
    }

    fun getLatestImage(): Bitmap? {
        return _images.lastOrNull() // Returns the latest image or null if the list is empty
    }
}
