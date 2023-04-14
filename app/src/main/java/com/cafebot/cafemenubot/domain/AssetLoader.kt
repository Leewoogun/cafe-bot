package com.cafebot.cafemenubot.domain

import android.content.Context
import android.util.Log
import org.json.JSONObject
import java.io.InputStream

class AssetLoader {

    fun getJsonString(context : Context) : String?{
        // 성공 혹은 실패로 나뉘어 지는 작업 처리 : runCatching
        return kotlin.runCatching {
            loadJson(context)
        }.getOrNull()
    }

    private fun loadJson(context : Context) : String{
        return context.assets.open("chatbot.json").use{ inputStream ->
            val size = inputStream.available()
            val bytes = ByteArray(size)
            inputStream.read(bytes)
            String(bytes)
        }
    }
}