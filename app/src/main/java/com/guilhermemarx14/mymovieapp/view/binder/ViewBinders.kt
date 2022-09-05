package com.guilhermemarx14.mymovieapp.view.binder

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("srcUrl")
fun ImageView.bindSrcUrl(
    url: String?
){
    Glide
        .with(this)
        .load(url ?: "")
        .centerInside()
        .into(this)
}