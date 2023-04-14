package com.cafebot.cafemenubot.common

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@BindingAdapter("todayDateBinding")
fun applyTodayDate(view : TextView, text : String){
    val currentDate = LocalDate.now()
    val formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")
    val formatDate = currentDate.format(formatter)

    view.text = formatDate
}



