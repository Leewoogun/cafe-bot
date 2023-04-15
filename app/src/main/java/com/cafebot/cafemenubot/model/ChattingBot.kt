package com.cafebot.cafemenubot.model

import com.google.gson.annotations.SerializedName

sealed class ChattingBot

data class Initial(
    @SerializedName("today_date")
    val todayDate : String,
    val name : String,
    @SerializedName("Image_Url")
    val imageUrl : String?,
    val text : String,
    @SerializedName("current_time")
    val currentTime : String,
) : ChattingBot()

data class ChatBot(
    val name : String,
    @SerializedName("Image_Url")
    val imageUrl : String,
    @SerializedName("current_time")
    val currentTime : String,
    val conversation : List<Conversation>,
    @SerializedName("recommend_drink")
    val recommendDrink: Drink
) : ChattingBot()
