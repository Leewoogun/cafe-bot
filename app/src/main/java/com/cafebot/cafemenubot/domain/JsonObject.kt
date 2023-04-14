package com.cafebot.cafemenubot.domain

import org.json.JSONObject

class JsonObject(private val chatBotData : JSONObject) {
    fun getInitialData(): JSONObject {
        return chatBotData.getJSONObject("initial")
    }
}
