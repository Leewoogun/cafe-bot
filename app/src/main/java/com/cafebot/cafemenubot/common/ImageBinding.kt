package com.cafebot.cafemenubot.common

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


@BindingAdapter("imageUrl")
fun loadImage(view : ImageView, imageUrl : String?){
    if (imageUrl == null){
        view.visibility = View.GONE
        return
    }

    if (!imageUrl.isNullOrEmpty()){
        view.visibility = View.VISIBLE
        Glide.with(view)
            .load(imageUrl)
            .into(view)

    }
}

@BindingAdapter("circleImageUrl")
fun loadCircleImage(view : ImageView, imageUrl : String?){
    if (imageUrl == null){
        view.visibility = View.GONE
        return
    }

    if (!imageUrl.isNullOrEmpty()){
        view.visibility = View.VISIBLE
        Glide.with(view)
            .load(imageUrl)
            .circleCrop()
            .into(view)
    }
}