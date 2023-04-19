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
        val indexList = getJsonArrayLengthIndex(sweetCoffees)
        Log.d("new" , "${sweetCoffees.getJSONObject(indexList[0])}")
        Log.d("new", "${sweetCoffees.getJSONObject(indexList[1])}")
        return listOf(sweetCoffees.getJSONObject(indexList[0]), sweetCoffees.getJSONObject(indexList[1]))
    }

    fun getRecommendSourCoffee() : List<JSONObject>{
        val chatBot = getChatBotData()
        val recommend = chatBot.getJSONObject("recommend_drink")
        val sourCoffee = recommend.getJSONArray("sour_coffee")
        val indexList = getJsonArrayLengthIndex(sourCoffee)

        return listOf(sourCoffee.getJSONObject(indexList[0]), sourCoffee.getJSONObject(indexList[1]))
    }

    fun getRecommendNonSourCoffee() : List<JSONObject>{
        val chatBot = getChatBotData()
        val recommend = chatBot.getJSONObject("recommend_drink")
        val nonSourCoffee = recommend.getJSONArray("non_sour_coffee")
        val indexList = getJsonArrayLengthIndex(nonSourCoffee)

        return listOf(nonSourCoffee.getJSONObject(indexList[0]), nonSourCoffee.getJSONObject(indexList[1]))
    }

    fun getRecommendLatte() : List<JSONObject>{
        val chatBot = getChatBotData()
        val recommend = chatBot.getJSONObject("recommend_drink")
        val latte = recommend.getJSONArray("Latte")
        val indexList = getJsonArrayLengthIndex(latte)

        return listOf(latte.getJSONObject(indexList[0]), latte.getJSONObject(indexList[1]))
    }

    fun getRecommendSmoothie() : List<JSONObject>{
        val chatBot = getChatBotData()
        val recommend = chatBot.getJSONObject("recommend_drink")
        val smoothie = recommend.getJSONArray("Smoothie")
        val indexList = getJsonArrayLengthIndex(smoothie)

        return listOf(smoothie.getJSONObject(indexList[0]), smoothie.getJSONObject(indexList[1]))
    }

    fun getRecommendTea() : List<JSONObject>{
        val chatBot = getChatBotData()
        val recommend = chatBot.getJSONObject("recommend_drink")
        val tea = recommend.getJSONArray("tea")
        val indexList = getJsonArrayLengthIndex(tea)

        return listOf(tea.getJSONObject(indexList[0]), tea.getJSONObject(indexList[1]))
    }


    private fun getJsonArrayLengthIndex(jsonArray: JSONArray) : List<Int>{
        val length = (0 until jsonArray.length()).toList()

        val newLength = length.shuffled(Random)
        Log.d("new", "$newLength")

        return listOf(newLength[0], newLength[1])
    }
}
