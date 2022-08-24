package com.example.catsbrowser.tools.bindings

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.petkeeper.R

@BindingAdapter(value = ["setPetImageFromUrl", "placeholder"])
fun loadImage(imageView: ImageView, url: String?, placeHolder: Drawable) {
    if (!url.isNullOrEmpty()) {

        Glide.with(imageView.context).load(url).centerInside()
            .placeholder(R.drawable.ic_launcher_background)
            .into(imageView)
    } else {
        imageView.setImageDrawable(placeHolder)
    }
}
