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
import com.cafebot.cafemenubot.model.ChattingBot
import com.cafebot.cafemenubot.model.Initial
import com.cafebot.cafemenubot.model.MyChatting
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val time = Time()
    private val chattingBot = mutableListOf<ChattingBot>()
    private val assetLoader = AssetLoader()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val chatBotData = assetLoader.getJsonString(this)

        if (!chatBotData.isNullOrEmpty()){
            val jsonObject = JSONObject(chatBotData)
            val initial = JsonObject(jsonObject).getInitialData()

            chattingBot.add(Initial(
                initial.getString("today_date"),
                initial.getString("name"),
                initial.getString("Image_Url"),
                initial.getString("text"),
                initial.getString("current_time")
            ))
        }

        val cafeMenuAdapter = CafeMenuAdapter(chattingBot)
        binding.rvChattingArea.adapter = cafeMenuAdapter

        cafeMenuAdapter.notifyDataSetChanged()
    }

    fun sendMessage(){
        binding.btnSend.setOnClickListener {
            val inputText = binding.etMessage.text.toString()
            val currentTime = time.getCurrentTime()

            val myChatting = MyChatting(inputText, currentTime)


        }
    }

//    fun setInitialAdapter(){
//        chattingBot.add(Initial())
//    }

}