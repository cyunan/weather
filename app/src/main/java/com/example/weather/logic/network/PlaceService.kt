package com.example.weather.logic.network

import com.example.weather.MyApplication
import com.example.weather.logic.model.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Author by CYN, Date on 2021/12/29
 * 地区请求API
 */
interface PlaceService {
    /**
     * 获取地区信息
     */
    @GET("v2/place?token=${MyApplication.TOKEN}&lang=zh_CN")
    fun searchPlaces(@Query("query") query: String): Call<PlaceResponse>

}