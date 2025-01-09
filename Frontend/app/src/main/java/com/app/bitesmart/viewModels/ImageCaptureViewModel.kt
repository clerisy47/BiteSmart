package com.app.bitesmart.viewModels

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel

class ImageCaptureViewModel : ViewModel() {
    private val _images = mutableListOf<Bitmap>()
    val images: List<Bitmap> get() = _images

    fun addImage(bitmap: Bitmap) {
        _images.add(bitmap)
    }

    fun getLatestImage(): Bitmap? {
        return _images.lastOrNull() // Returns the latest image or null if the list is empty
    }
}
