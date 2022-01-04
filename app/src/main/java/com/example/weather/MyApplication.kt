package com.example.weather

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

/**
 * Author by CYN, Date on 2021/12/29
 */
class MyApplication :Application(){
    companion object{
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
        const val TOKEN = ""
    }
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}