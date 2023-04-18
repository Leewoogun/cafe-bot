package com.cafebot.cafemenubot.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.cafebot.cafemenubot.R
import com.cafebot.cafemenubot.constant.COFFEE_NON_COFFEE
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
            firstQuestion(inputText, currentTime)
            secondQuestion(inputText, currentTime)

            setChatBotBindingData()
        }
    }

    private fun firstQuestion(inputText : String, currentTime : String){
        if (inputText.isNotEmpty()){
            return when (inputText) {
                "커피" -> {
                    val message = MyChatting(inputText, currentTime)
                    cafeMenuAdapter.addMessage(message)
                    binding.etMessage.text = null
                    sendText = inputText
                }
                "논 커피" -> {
                    val message = MyChatting(inputText, currentTime)
                    cafeMenuAdapter.addMessage(message)
                    binding.etMessage.text = null
                    sendText = inputText
                }
                else -> {
                    Toast.makeText(this, COFFEE_NON_COFFEE, Toast.LENGTH_SHORT).show()
                    binding.etMessage.text = null
                }
            }
        }
    }

    private fun secondQuestion(inputText : String, currentTime : String){
        if (sendText == "커피"){
            if (inputText == "단 커피"){

            }
            else if (inputText == "달지 않은 커피"){
                val message = MyChatting(inputText, currentTime)
                cafeMenuAdapter.addMessage(message)
                binding.etMessage.text = null
                sendText = inputText
            }
        }
    }

    private fun setChatBotBindingData(){
        val jsonObject = JsonObject(this)
        val chatBot = jsonObject.getChatBotData()
        val coffee = jsonObject.getSelectCoffee().getJSONObject("coffee")
        val nonCoffee = jsonObject.getSelectNonCoffee().getJSONObject("non_coffee")
        val nonSweetCoffee = jsonObject.getSelectNonSweetCoffee().getJSONObject("non_sweet_coffee")

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
        else if(sendText == "논 커피"){
            val message = ChatBotData(
                chatBot.getString("name"),
                chatBot.getString("Image_Url"),
                chatBot.getString("current_time"),
                nonCoffee.getString("text"),
                null,
                null
            )
            cafeMenuAdapter.addMessage(message)
        }
        else if (sendText == "달지 않은 커피"){
            val message = ChatBotData(
                chatBot.getString("name"),
                chatBot.getString("Image_Url"),
                chatBot.getString("current_time"),
                nonSweetCoffee.getString("text",),
                null,
                null
            )
            cafeMenuAdapter.addMessage(message)
        }
    }
}