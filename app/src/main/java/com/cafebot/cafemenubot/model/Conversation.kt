package com.cafebot.cafemenubot.model

import com.google.gson.annotations.SerializedName

data class Conversation(
    val coffee : Coffee,
    @SerializedName("non_sweet_coffee")
    val nonSweetCoffee : Coffee,
    @SerializedName("non_coffee")
    val nonCoffee : Coffee
)

data class Coffee(
    val text : String,
    val option : List<Option>?
)

