package com.cafebot.cafemenubot.model

import com.google.gson.annotations.SerializedName

data class Initial(
    @SerializedName("today_date")
    val todayDate : String,
    val text : String
)
