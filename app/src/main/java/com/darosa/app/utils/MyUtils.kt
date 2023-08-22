package com.darosa.app.utils

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun ImageView.loadImageUrl(url: String) {
    Glide.with(this.context.applicationContext)
        .load(url)
        .centerCrop()
        .into(this)
}

fun ImageView.loadImage(img: Int) {
    Glide.with(this.context.applicationContext)
        .load(img)
        .into(this)
}