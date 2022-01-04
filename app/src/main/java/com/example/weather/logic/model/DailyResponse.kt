package com.example.weather.logic.model

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Author by CYN, Date on 2021/12/31
 * 未来几天天气信息接口所返回的JSON数据
 */
data class DailyResponse(val status: String, val result: Result) {
    data class Result(val daily: Daily)
    data class Daily(val temperature: List<Temperature>, val skycon: List<Skycon>,
                     @SerializedName("life_index") val lifeIndex: LifeIndex)
    data class Temperature(val max: Float, val min: Float)
    data class Skycon(val value: String, val date: Date)
    data class LifeIndex(val coldRisk: List<LifeDescription>, val carWashing:
    List<LifeDescription>, val ultraviolet: List<LifeDescription>,
                         val dressing: List<LifeDescription>)
    data class LifeDescription(val desc: String)
}