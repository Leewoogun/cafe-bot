package com.cafebot.cafemenubot.common

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@BindingAdapter("todayDate")
fun applyTodayDate(view : TextView){
    val currentDate = LocalDate.now()
    val formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")
    val formatDate = currentDate.format(formatter)

    view.text = formatDate
}



