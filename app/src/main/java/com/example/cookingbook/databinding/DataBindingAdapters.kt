package com.example.cookingbook.databinding

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.example.cookingbook.R


@BindingAdapter("netImage")
fun ImageView.netImage(url: String?) {
    Glide.with(this.context)
        .load(url)
        .thumbnail(Glide.with(this.context).load(R.drawable.bubble_preloader))
        .transition(withCrossFade())
        .centerCrop()
        .into(this)
}

@BindingAdapter("textWithDots")
fun TextView.textWithDots(text: String) {
    if (text.length > 50) {
        this.text = "${text.subSequence(0, 25)}..."
    } else {
        this.text = text
    }
}