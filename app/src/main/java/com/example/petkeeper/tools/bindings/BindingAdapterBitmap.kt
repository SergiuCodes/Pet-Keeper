package com.example.petkeeper.tools.bindings

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.petkeeper.R

@BindingAdapter("bind:imageBitmap")
fun loadImage(iv: ImageView, bitmap: Bitmap) {
    iv.setImageBitmap(bitmap)
}