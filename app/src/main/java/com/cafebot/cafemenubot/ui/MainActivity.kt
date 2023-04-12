package com.cafebot.cafemenubot.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cafebot.cafemenubot.R
import com.cafebot.cafemenubot.databinding.ActivityMainBinding
import com.cafebot.cafemenubot.domain.Time
import com.cafebot.cafemenubot.model.MyChatting

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val time = Time()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

    fun sendMessage(){
        binding.btnSend.setOnClickListener {
            val inputText = binding.etMessage.text.toString()
            val currentTime = time.getCurrentTime()

            val myChatting = MyChatting(inputText, currentTime)


        }
    }
}