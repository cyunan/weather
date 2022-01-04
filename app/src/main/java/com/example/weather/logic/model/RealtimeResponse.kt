package com.example.weather.logic.model

import com.google.gson.annotations.SerializedName

/**
 * Author by CYN, Date on 2021/12/31
 * 实时天气信息接口所返回的JSON数据
 */
data class RealtimeResponse(val status: String, val result: Result) {
    data class Result(val realtime: Realtime)
    data class Realtime(val skycon: String, val temperature: Float,
                        @SerializedName("air_quality") val airQuality: AirQuality)
    data class AirQuality(val aqi: AQI)
    data class AQI(val chn: Float)
}