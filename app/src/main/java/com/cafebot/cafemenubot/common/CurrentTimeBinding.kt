package com.cafebot.cafemenubot.common

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.cafebot.cafemenubot.domain.Time

@BindingAdapter("currentTime")
fun currentTime(view : TextView){
    val time = Time()
    view.text = time.getCurrentTime()

}

