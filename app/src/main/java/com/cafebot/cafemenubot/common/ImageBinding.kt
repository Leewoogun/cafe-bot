package com.cafebot.cafemenubot.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


@BindingAdapter("imageUrl")
fun loadImage(view : ImageView, imageUrl : String?){
    // TODO Glide 매개변수 확인하기
    if (!imageUrl.isNullOrEmpty()){
        Glide.with(view)
            .load(imageUrl)
            .into(view)

    }
}

@BindingAdapter("circleImageUrl")
fun loadCircleImage(view : ImageView, imageUrl : String?){
    if (!imageUrl.isNullOrEmpty()){
        Glide.with(view)
            .load(imageUrl)
            .circleCrop()
            .into(view)
    }
}