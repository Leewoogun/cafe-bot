package com.cafebot.cafemenubot.domain

import android.content.Context
import android.util.Log
import org.json.JSONObject

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
}
