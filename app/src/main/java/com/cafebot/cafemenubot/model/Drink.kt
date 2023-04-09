package com.cafebot.cafemenubot.model

import com.google.gson.annotations.SerializedName

data class Drink(
    @SerializedName("sweet_coffee")
    val sweetCoffee : List<RecommendDrink>?,
    @SerializedName("sour_coffee")
    val sourCoffee : List<RecommendDrink>?,
    @SerializedName("non_sour_coffee")
    val nonSourCoffee : List<RecommendDrink>?,
    @SerializedName("Latte")
    val latte : List<RecommendDrink>?,
    @SerializedName("Smoothie")
    val Smoothie : List<RecommendDrink>?,
    @SerializedName("tea")
    val tea : List<RecommendDrink>?
)

data class RecommendDrink(
    val variance : String,
    @SerializedName("Image_Url")
    val imageUrl : String?
)
