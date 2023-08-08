package com.lock.the.box.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("image")
fun loadImage(view: ImageView, url: String) {
    Glide.with(view)
        .load(url)
        .into(view)
}

@BindingAdapter("imageUrl")
fun ImageView.setImageUrl( url: String?) {
    Glide.with(this.context).load(url).into(this)
}