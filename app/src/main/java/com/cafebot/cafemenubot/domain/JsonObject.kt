package com.cafebot.cafemenubot.domain

import android.content.Context
import android.util.Log
import org.json.JSONArray
import org.json.JSONObject
import kotlin.random.Random


class JsonObject(private val context : Context) {
    private val assetLoader = AssetLoader()

    private fun setChatBotJson() : JSONObject{
        val chatBotData = assetLoader.getJsonString(context)

        if(!chatBotData.isNullOrEmpty()) {
            return JSONObject(chatBotData)
        }
        else throw IllegalArgumentException("fail to set JSON : $chatBotData")
    }

    fun getInitialData(): JSONObject {
        val jsonObject = setChatBotJson()
        return jsonObject.getJSONObject("initial")
    }

    fun getChatBotData() : JSONObject{
        val jsonObject = setChatBotJson()
        return jsonObject.getJSONObject("chat_bot")

    }

    fun getSelectCoffee(): JSONObject{
        val chatBot = getChatBotData()
        val conversation = chatBot.getJSONArray("conversation")

        return conversation.getJSONObject(0)
    }

    fun getSelectNonCoffee() : JSONObject{
        val chatBot = getChatBotData()
        val conversation = chatBot.getJSONArray("conversation")

        return conversation.getJSONObject(2)
    }

    fun getSelectNonSweetCoffee() : JSONObject{
        val chatBot = getChatBotData()
        val conversation = chatBot.getJSONArray("conversation")

        return conversation.getJSONObject(1)
    }

    fun getRecommendText(): String {
        val chatBot = getChatBotData()
        val recommend = chatBot.getJSONObject("recommend_drink")
        return recommend.getString("text")
    }


    fun getRecommendSweetCoffee() : List<JSONObject> {
        val chatBot = getChatBotData()
        val recommend = chatBot.getJSONObject("recommend_drink")
        val sweetCoffees = recommend.getJSONArray("sweet_coffee")

        return jsonArrayShuffle(sweetCoffees)
    }

    fun getRecommendSourCoffee() : List<JSONObject>{
        val chatBot = getChatBotData()
        val recommend = chatBot.getJSONObject("recommend_drink")
        val sourCoffee = recommend.getJSONArray("sour_coffee")

        return jsonArrayShuffle(sourCoffee)
    }

    fun getRecommendNonSourCoffee() : List<JSONObject>{
        val chatBot = getChatBotData()
        val recommend = chatBot.getJSONObject("recommend_drink")
        val nonSourCoffee = recommend.getJSONArray("non_sour_coffee")

        return jsonArrayShuffle(nonSourCoffee)
    }

    fun getRecommendLatte() : List<JSONObject>{
        val chatBot = getChatBotData()
        val recommend = chatBot.getJSONObject("recommend_drink")
        val latte = recommend.getJSONArray("Latte")

        return jsonArrayShuffle(latte)
    }

    fun getRecommendSmoothie() : List<JSONObject>{
        val chatBot = getChatBotData()
        val recommend = chatBot.getJSONObject("recommend_drink")
        val smoothie = recommend.getJSONArray("Smoothie")

        return jsonArrayShuffle(smoothie)
    }

    fun getRecommendTea() : List<JSONObject>{
        val chatBot = getChatBotData()
        val recommend = chatBot.getJSONObject("recommend_drink")
        val tea = recommend.getJSONArray("tea")

        return jsonArrayShuffle(tea)
    }


    private fun getJsonArrayLengthIndex(jsonArray: JSONArray) : List<Int>{
        val length = (0 until jsonArray.length()).toList()
        val newLength = length.shuffled()

        return listOf(newLength[0], newLength[1])
    }

    private fun jsonArrayShuffle(jsonArray : JSONArray) : List<JSONObject>{
        val list = (0 until jsonArray.length()).map { jsonArray[it] }.toMutableList().shuffled()
        val castedList = list.map { it as JSONObject }

        return listOf(castedList[0], castedList[1])
    }
}
