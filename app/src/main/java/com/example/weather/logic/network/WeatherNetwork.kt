package com.example.weather.logic.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine
import kotlin.math.ln

/**
 * Author by CYN, Date on 2021/12/29
 */
object WeatherNetwork {
    /**
     * 创建一个PlaceService接口的动态代理对象
     */
    private val placeService = ServiceCreator.create<PlaceService>()
    /**
     * 获取地区信息
     */
    suspend fun searchPlaces(query: String) = placeService.searchPlaces(query).await()

    /**
     * 创建一个weatherService接口的动态代理对象
     */
    private val weatherService = ServiceCreator.create<WeatherService>()

    /**
     * 获取未来的天气信息
     */
    suspend fun getDailyWeather(lng :String, lat: String) =
        weatherService.getDailyWeather(lng, lat).await()

    /**
     * 获取实时的天气信息
     */
    suspend fun getRealtimeWeather(lng: String, lat: String) =
        weatherService.getRealtimeWeather(lng, lat).await()


    /**
     * 创建一个Call的扩展函数
     */
    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null) continuation.resume(body)
                    else continuation.resumeWithException(
                        RuntimeException("response body is null"))
                }
                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                } })
        } }
}