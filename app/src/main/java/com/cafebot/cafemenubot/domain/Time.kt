package com.cafebot.cafemenubot.domain

import android.service.autofill.Validators.or
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private const val MORNING = "오전"
private const val AFTERNOON = "오후"

class Time {
    fun getCurrentTime() : String{
        val hour = makeCurrentTime().split(":")[0]
        val minute = makeCurrentTime().split(":")[1]

        return when(val intHour = hour.toInt()){
            0 -> "$MORNING 12:$minute"
            12 -> "$AFTERNOON 12:$minute"
            in 13..23 -> AFTERNOON + " " + (intHour - 12).toString() + ":" + minute
            in 1..11 -> "$MORNING $hour:$minute"

            else -> throw IllegalArgumentException("잘못된 시간입니다.")
        }
    }

    private fun makeCurrentTime(): String {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ISO_LOCAL_TIME

        return current.format(formatter)
    }

}