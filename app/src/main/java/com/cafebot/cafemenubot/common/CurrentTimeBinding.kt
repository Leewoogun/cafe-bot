package com.cafebot.cafemenubot.common

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.cafebot.cafemenubot.domain.Time

@BindingAdapter("currentTimeBinding")
fun currentTime(view : TextView, text : String){
    val time = Time()
    view.text = time.getCurrentTime()
}

