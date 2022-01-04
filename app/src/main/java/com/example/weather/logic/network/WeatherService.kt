package com.example.weather.logic.network

import com.example.weather.MyApplication
import com.example.weather.logic.model.DailyResponse
import com.example.weather.logic.model.RealtimeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Author by CYN, Date on 2021/12/31
 * 天气信息API
 */
interface WeatherService {
    /**
     * 获取实时的天气信息
     */
    @GET("v2.5/${MyApplication.TOKEN}/{lng},{lat}/realtime.json")
    fun getRealtimeWeather(@Path("lng") lng: String, @Path("lat") lat: String):
            Call<RealtimeResponse>

    /**
     * 获取未来的天气信息
     */
    @GET("v2.5/${MyApplication.TOKEN}/{lng},{lat}/daily.json")
    fun getDailyWeather(@Path("lng") lng: String, @Path("lat") lat: String): Call<DailyResponse>
}