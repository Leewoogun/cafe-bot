package com.cafebot.cafemenubot.ui

import android.os.Build.VERSION_CODES.P
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.cafebot.cafemenubot.R
import com.cafebot.cafemenubot.constant.*
import com.cafebot.cafemenubot.databinding.ActivityMainBinding
import com.cafebot.cafemenubot.domain.AssetLoader
import com.cafebot.cafemenubot.domain.Flag
import com.cafebot.cafemenubot.domain.Flag.finishFlag
import com.cafebot.cafemenubot.domain.Flag.firstFlag
import com.cafebot.cafemenubot.domain.Flag.secondFlag
import com.cafebot.cafemenubot.domain.Flag.thirdFlag
import com.cafebot.cafemenubot.domain.Flag.toastFlag
import com.cafebot.cafemenubot.domain.JsonObject
import com.cafebot.cafemenubot.domain.Time
import com.cafebot.cafemenubot.model.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val time = Time()
    private lateinit var sendText : String
    private var backPressedTime: Long = 0 // 뒤로가기 버튼을 누른 시간을 저장할 변수

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val jsonObject = JsonObject(this) // context 객체는 onCreate 메소드 이전엔 null
        val initial = jsonObject.getInitialData()
        val initialMessage = Initial(
            initial.getString("today_date"),
            initial.getString("name"),
            initial.getString("Image_Url"),
            initial.getString("text"),
            initial.getString("current_time")
        )
        val cafeMenuAdapter = CafeMenuAdapter(binding.rvChattingArea)

        binding.rvChattingArea.adapter = cafeMenuAdapter
        cafeMenuAdapter.addMessage(initialMessage)

        sendMessage(initialMessage, cafeMenuAdapter)
    }

    private fun sendMessage(initialMessage : Initial, cafeMenuAdapter : CafeMenuAdapter){
        binding.btnSend.setOnClickListener {
            val inputText = binding.etMessage.text.toString()
            val currentTime = time.getCurrentTime()

            if (firstFlag){
                firstQuestion(inputText, currentTime, cafeMenuAdapter)
            }
            if (secondFlag){
                secondQuestion(inputText, currentTime, cafeMenuAdapter)
                firstFlag = false
            }
            if (thirdFlag){
                thirdQuestion(inputText, currentTime, cafeMenuAdapter)
                secondFlag = false
            }
            if (finishFlag){
                resetChatBot(initialMessage, cafeMenuAdapter)
            }
        }
    }

    private fun firstQuestion(inputText : String, currentTime : String, cafeMenuAdapter : CafeMenuAdapter){
        if (inputText.isNotEmpty()){
            return when (inputText) {
                "커피" -> {
                    val message = MyChatting(inputText, currentTime)
                    cafeMenuAdapter.addMessage(message)
                    binding.etMessage.text = null
                    sendText = inputText
                    firstFlag = false
                    secondFlag = true
                    setChatBotBindingData(cafeMenuAdapter)
                }
                "논 커피" -> {
                    val message = MyChatting(inputText, currentTime)
                    cafeMenuAdapter.addMessage(message)
                    binding.etMessage.text = null
                    sendText = inputText
                    firstFlag = false
                    secondFlag = true
                    setChatBotBindingData(cafeMenuAdapter)
                }
                else -> {
                    if (firstFlag){
                        Toast.makeText(this, COFFEE_NON_COFFEE, Toast.LENGTH_SHORT).show()
                        binding.etMessage.text = null
                    } else {

                    }
                }
            }
        }
    }

    private fun secondQuestion(inputText : String, currentTime : String, cafeMenuAdapter : CafeMenuAdapter){
        if (sendText == "커피"){
            when (inputText) {
                "단 커피" -> {
                    val message = MyChatting(inputText, currentTime)
                    cafeMenuAdapter.addMessage(message)
                    binding.etMessage.text = null
                    sendText = inputText
                    secondFlag = false
                    setChatBotBindingData(cafeMenuAdapter)
                }
                "달지 않은 커피" -> {
                    val message = MyChatting(inputText, currentTime)
                    cafeMenuAdapter.addMessage(message)
                    binding.etMessage.text = null
                    sendText = inputText
                    secondFlag = false
                    thirdFlag = true
                    setChatBotBindingData(cafeMenuAdapter)
                }
                else -> {
                    if (secondFlag){
                        Toast.makeText(this, SWEET_NON_SWEET, Toast.LENGTH_SHORT).show()
                        binding.etMessage.text = null
                    }
                }
            }
        }
        else if(sendText == "논 커피"){
            when (inputText) {
                "라떼" -> {
                    val message = MyChatting(inputText, currentTime)
                    cafeMenuAdapter.addMessage(message)
                    binding.etMessage.text = null
                    sendText = inputText
                    secondFlag = false
                    setChatBotBindingData(cafeMenuAdapter)
                }
                "스무디" -> {
                    val message = MyChatting(inputText, currentTime)
                    cafeMenuAdapter.addMessage(message)
                    binding.etMessage.text = null
                    sendText = inputText
                    secondFlag = false
                    setChatBotBindingData(cafeMenuAdapter)
                }
                "차" -> {
                    val message = MyChatting(inputText, currentTime)
                    cafeMenuAdapter.addMessage(message)
                    binding.etMessage.text = null
                    sendText = inputText
                    secondFlag = false
                    setChatBotBindingData(cafeMenuAdapter)
                }
                else -> {
                    if (secondFlag){
                        Toast.makeText(this, LATTE_SMOOTHIE_TEA, Toast.LENGTH_SHORT).show()
                        binding.etMessage.text = null
                    }
                }
            }
        }
    }

    private fun thirdQuestion(inputText : String, currentTime : String, cafeMenuAdapter : CafeMenuAdapter){
        if (sendText == "달지 않은 커피"){
            when (inputText) {
                "산미 있는거" -> {
                    val message = MyChatting(inputText, currentTime)
                    cafeMenuAdapter.addMessage(message)
                    sendText = inputText
                    binding.etMessage.text = null
                    thirdFlag = false
                    setChatBotBindingData(cafeMenuAdapter)

                }
                "산미 없는거" -> {
                    val message = MyChatting(inputText, currentTime)
                    cafeMenuAdapter.addMessage(message)
                    sendText = inputText
                    binding.etMessage.text = null
                    thirdFlag = false
                    setChatBotBindingData(cafeMenuAdapter)
                }
                else ->{
                    Toast.makeText(this, SOUR_NON_SOUR, Toast.LENGTH_SHORT).show()
                    binding.etMessage.text = null
                }
            }
        }
    }

    private fun setChatBotBindingData(cafeMenuAdapter : CafeMenuAdapter){
        val jsonObject = JsonObject(this)
        val chatBot = jsonObject.getChatBotData()
        val coffee = jsonObject.getSelectCoffee().getJSONObject("coffee")
        val nonCoffee = jsonObject.getSelectNonCoffee().getJSONObject("non_coffee")
        val nonSweetCoffee = jsonObject.getSelectNonSweetCoffee().getJSONObject("non_sweet_coffee")
        val recommendText = jsonObject.getRecommendText()
        val retryMessage = ChatBotData(
            chatBot.getString("name"),
            chatBot.getString("Image_Url"),
            chatBot.getString("current_time"),
            "추천 시스템이 끝났습니다.\n새로운 대화를 시작하려면 1을 입력\n종료하려면 뒤로가기 버튼을 눌려주세요.!",
            null,
            null,
            null,
            null
        )

        when (sendText) {
            "커피" -> {
                val message = ChatBotData(
                    chatBot.getString("name"),
                    chatBot.getString("Image_Url"),
                    chatBot.getString("current_time"),
                    coffee.getString("text"),
                    null,
                    null,
                    null,
                    null
                )
                cafeMenuAdapter.addMessage(message)
            }
            "논 커피" -> {
                val message = ChatBotData(
                    chatBot.getString("name"),
                    chatBot.getString("Image_Url"),
                    chatBot.getString("current_time"),
                    nonCoffee.getString("text"),
                    null,
                    null,
                    null,
                    null
                )
                cafeMenuAdapter.addMessage(message)
            }
            "달지 않은 커피" -> {
                val message = ChatBotData(
                    chatBot.getString("name"),
                    chatBot.getString("Image_Url"),
                    chatBot.getString("current_time"),
                    nonSweetCoffee.getString("text",),
                    null,
                    null,
                    null,
                    null
                )
                cafeMenuAdapter.addMessage(message)
            }
            "단 커피" ->{
                val recommendSweetCoffee = jsonObject.getRecommendSweetCoffee()
                Log.d("drink", recommendSweetCoffee[0].getString("variance"))
                Log.d("drink", recommendSweetCoffee[1].getString("variance"))
                val message = ChatBotData(
                    chatBot.getString("name"),
                    chatBot.getString("Image_Url"),
                    chatBot.getString("current_time"),
                    recommendText,
                    recommendSweetCoffee[0].getString("Image_Url"),
                    recommendSweetCoffee[0].getString("variance"),
                    recommendSweetCoffee[1].getString("Image_Url"),
                    recommendSweetCoffee[1].getString("variance")
                )
                cafeMenuAdapter.addMessage(message)
                cafeMenuAdapter.addMessage(retryMessage)
                finishFlag = true
            }
            "산미 있는거" ->{
                val recommendSourCoffee = jsonObject.getRecommendSourCoffee()
                val message = ChatBotData(
                    chatBot.getString("name"),
                    chatBot.getString("Image_Url"),
                    chatBot.getString("current_time"),
                    recommendText,
                    recommendSourCoffee[0].getString("Image_Url"),
                    recommendSourCoffee[0].getString("variance"),
                    recommendSourCoffee[1].getString("Image_Url"),
                    recommendSourCoffee[1].getString("variance")
                )
                cafeMenuAdapter.addMessage(message)
                cafeMenuAdapter.addMessage(retryMessage)
                finishFlag = true
            }
            "산미 없는거" ->{
                val recommendNonSourCoffee = jsonObject.getRecommendNonSourCoffee()
                val message = ChatBotData(
                    chatBot.getString("name"),
                    chatBot.getString("Image_Url"),
                    chatBot.getString("current_time"),
                    recommendText,
                    recommendNonSourCoffee[0].getString("Image_Url"),
                    recommendNonSourCoffee[0].getString("variance"),
                    recommendNonSourCoffee[1].getString("Image_Url"),
                    recommendNonSourCoffee[1].getString("variance")
                )
                cafeMenuAdapter.addMessage(message)
                cafeMenuAdapter.addMessage(retryMessage)
                finishFlag = true
            }
            "라떼" ->{
                val recommendLatte = jsonObject.getRecommendLatte()
                val message = ChatBotData(
                    chatBot.getString("name"),
                    chatBot.getString("Image_Url"),
                    chatBot.getString("current_time"),
                    recommendText,
                    recommendLatte[0].getString("Image_Url"),
                    recommendLatte[0].getString("variance"),
                    recommendLatte[1].getString("Image_Url"),
                    recommendLatte[1].getString("variance")
                )
                cafeMenuAdapter.addMessage(message)
                cafeMenuAdapter.addMessage(retryMessage)
                finishFlag = true
            }
            "스무디" ->{
                val recommendSmoothie = jsonObject.getRecommendSmoothie()
                val message = ChatBotData(
                    chatBot.getString("name"),
                    chatBot.getString("Image_Url"),
                    chatBot.getString("current_time"),
                    recommendText,
                    recommendSmoothie[0].getString("Image_Url"),
                    recommendSmoothie[0].getString("variance"),
                    recommendSmoothie[1].getString("Image_Url"),
                    recommendSmoothie[1].getString("variance")
                )
                cafeMenuAdapter.addMessage(message)
                cafeMenuAdapter.addMessage(retryMessage)
                finishFlag = true
            }
            "차" ->{
                val recommendTea = jsonObject.getRecommendTea()
                val message = ChatBotData(
                    chatBot.getString("name"),
                    chatBot.getString("Image_Url"),
                    chatBot.getString("current_time"),
                    recommendText,
                    recommendTea[0].getString("Image_Url"),
                    recommendTea[0].getString("variance"),
                    recommendTea[1].getString("Image_Url"),
                    recommendTea[1].getString("variance")
                )
                cafeMenuAdapter.addMessage(message)
                cafeMenuAdapter.addMessage(retryMessage)
                finishFlag = true
            }
        }
    }

    private fun resetChatBot(message : Initial, cafeMenuAdapter : CafeMenuAdapter){
        val inputText = binding.etMessage.text.toString()

        if (inputText == "1"){
            cafeMenuAdapter.resetChatBot()
            cafeMenuAdapter.notifyDataSetChanged()
            cafeMenuAdapter.addMessage(message)
            binding.etMessage.text = null
            finishFlag = false
            firstFlag = true
            toastFlag = false
        }
        else if (toastFlag){
            Toast.makeText(this,FINISH,Toast.LENGTH_SHORT).show()
            binding.etMessage.text = null
        }
        else{
            toastFlag = true
        }
    }

    override fun onBackPressed() {
        val delay: Long = 2000 // 2초의 딜레이를 줍니다.
        firstFlag = true
        finishFlag = false

        if (backPressedTime + delay > System.currentTimeMillis()) {
            finish()
        } else {
            Toast.makeText(this, "한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()
            backPressedTime = System.currentTimeMillis()
        }
    }
}