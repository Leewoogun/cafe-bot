package com.cafebot.cafemenubot.model

import com.google.gson.annotations.SerializedName


data class ChattingBotDTO(
    @SerializedName("today_date")
    val todayDate: TodayDate,
    @SerializedName("chat_bot")
    val chatBot: ChatBot,
    @SerializedName("sweet_coffee")
    val sweetCoffee: List<Drink>,
    @SerializedName("sour_coffee")
    val sourCoffee: List<Drink>,
    @SerializedName("non_sour_coffee")
    val nonSourCoffee: List<Drink>,
    val Latte: List<Drink>,
    val Smoothie: List<Drink>,
    val tea: List<Drink>
)

data class TodayDate(
    val text : String
)

data class ChatBot(
    val name: String,
    @SerializedName("Image_Url")
    val imageUrl: String?,
    @SerializedName("current_time")
    val currentTime: String,
    val conversation: List<Conversation>
)

data class Conversation(
    val coffee: ChatBotOption?,
    @SerializedName("non_sweet_coffee")
    val nonSweetCoffee: ChatBotOption?,
    @SerializedName("non_coffee")
    val nonCoffee: ChatBotOption?
)

data class ChatBotOption(
    val text: String,
    val type: String
)

data class Drink(
    val variance: String,
    @SerializedName("Image_Url")
    val imageUrl: String?
)




