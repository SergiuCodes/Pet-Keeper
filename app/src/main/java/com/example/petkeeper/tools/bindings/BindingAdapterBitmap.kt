package com.example.petkeeper.tools.bindings

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("bind:imageBitmap")
fun loadImage(iv: ImageView, bitmap: Bitmap) {
    iv.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 400, 150, false))
}