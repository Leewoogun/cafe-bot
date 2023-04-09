package com.cafebot.cafemenubot.model

import com.google.gson.annotations.SerializedName

data class ChattingBot(
    val initial: Initial,
    @SerializedName("chat_bot")
    val chatBot : ChatBot,
    val drink : Drink
)

data class ChatBot(
    val name : String,
    @SerializedName("Image_Url")
    val imageUrl : String,
    @SerializedName("current_time")
    val currentTime : String,
    val conversation : List<Conversation>
)
