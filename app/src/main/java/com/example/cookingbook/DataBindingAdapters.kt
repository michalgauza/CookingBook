package com.example.cookingbook

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade


@BindingAdapter("netImage")
fun ImageView.netImage(url: String) {
    Glide.with(this.context)
        .load(url)
        .thumbnail(Glide.with(this.context).load(R.drawable.bubble_preloader))
        .centerCrop()
        .transition(withCrossFade())
        .into(this)
}