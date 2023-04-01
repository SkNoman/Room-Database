package com.example.roomapp.utils

import android.util.Log

object MyLog {
    fun printLog(identifier: String, message: Any){
        Log.d(identifier,message.toString())
    }
}