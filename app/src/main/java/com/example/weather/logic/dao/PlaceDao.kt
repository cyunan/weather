package com.example.weather.logic.dao

import android.content.Context
import android.provider.Settings.Global.putString
import androidx.core.content.edit
import com.example.weather.MyApplication
import com.example.weather.logic.model.Place
import com.google.gson.Gson

/**
 * Author by CYN, Date on 2022/1/2
 */
object PlaceDao {
    fun savePlace(place: Place)  = sharedPreferences().edit {
        putString("place", Gson().toJson(place))
    }
    fun getSavedPlace(): Place {
        val placeJson = sharedPreferences().getString("place", "")
        return Gson().fromJson(placeJson, Place::class.java)
    }
    fun isPlaceSaved() = sharedPreferences().contains("place")
    private fun sharedPreferences() = MyApplication.context.
        getSharedPreferences("sunny_weather", Context.MODE_PRIVATE)
}