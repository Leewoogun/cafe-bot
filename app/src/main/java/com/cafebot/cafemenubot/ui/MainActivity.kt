package com.cafebot.cafemenubot.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import com.cafebot.cafemenubot.R
import com.cafebot.cafemenubot.databinding.ActivityMainBinding
import com.cafebot.cafemenubot.domain.AssetLoader
import com.cafebot.cafemenubot.domain.JsonObject
import com.cafebot.cafemenubot.domain.Time
import com.cafebot.cafemenubot.model.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val time = Time()
    private val chattingBot = mutableListOf<ChattingBot>()
    private val cafeMenuAdapter = CafeMenuAdapter(chattingBot)
//    private val jsonObject = JsonObject(this)
//    private val initial = jsonObject.getInitialData()
    private lateinit var sendText : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val jsonObject = JsonObject(this) // context 객체는 onCreate 메소드 이전엔 null
        val initial = jsonObject.getInitialData()

        chattingBot.add(Initial(
            initial.getString("today_date"),
            initial.getString("name"),
            initial.getString("Image_Url"),
            initial.getString("text"),
            initial.getString("current_time")))

        binding.rvChattingArea.adapter = cafeMenuAdapter
        cafeMenuAdapter.notifyDataSetChanged()

        sendMessage()


    }

    private fun sendMessage(){
        binding.btnSend.setOnClickListener {
            val inputText = binding.etMessage.text.toString()
            val currentTime = time.getCurrentTime()


            if (inputText.isNotEmpty()){
                if (inputText == "커피"){
                    val message = MyChatting(inputText, currentTime)
                    cafeMenuAdapter.addMessage(message)
                    binding.etMessage.text = null
                    sendText = inputText
                }
            }
            setChatBotBindingData()
        }
    }

    private fun setChatBotBindingData(){
        val jsonObject = JsonObject(this)
        val chatBot = jsonObject.getChatBotData()
        val coffee = jsonObject.getSelectCoffee().getJSONObject("coffee")

        if (sendText == "커피"){
            val message = ChatBotData(
                chatBot.getString("name"),
                chatBot.getString("Image_Url"),
                chatBot.getString("current_time"),
                coffee.getString("text"),
                null,
                null
            )

            cafeMenuAdapter.addMessage(message)
        }
    }

//    fun setInitialAdapter(){
//        chattingBot.add(Initial())
//    }

}