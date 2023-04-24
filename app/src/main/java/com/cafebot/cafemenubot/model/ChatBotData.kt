package com.cafebot.cafemenubot.model

data class ChatBotData(
    val name : String,
    val imageUrl : String,
    val currentTime : String,
    val text : String,
    val recommendImageUrl : String?,
    val recommendText : String?,
    val recommendImageUrl2: String?,
    val recommendText2 : String?
) : ChattingBot()

