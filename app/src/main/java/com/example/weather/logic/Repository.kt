package com.example.weather.logic

import android.content.Context
import androidx.lifecycle.liveData
import com.example.weather.logic.dao.PlaceDao
import com.example.weather.logic.model.Place
import com.example.weather.logic.model.Weather
import com.example.weather.logic.network.WeatherNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import retrofit2.Call
import kotlin.coroutines.CoroutineContext


/**
 * Author by CYN, Date on 2021/12/30
 */
object Repository {


    fun searchPlaces(query: String) = fire(Dispatchers.IO){
        val placeResponse = WeatherNetwork.searchPlaces(query)
        if (placeResponse.status == "ok") {
            val places = placeResponse.places
            Result.success(places)
        } else {
            Result.failure(RuntimeException("response status is ${placeResponse.status}"))
        }
    }

    fun refreshWeather(lng: String, lat: String) = fire(Dispatchers.IO){
        coroutineScope {
            val deferredRealtime = async {
                WeatherNetwork.getRealtimeWeather(lng, lat)
            }
            val deferredDaily = async {
                WeatherNetwork.getDailyWeather(lng, lat)
            }
            val realtimeResponse = deferredRealtime.await()
            val dailyResponse = deferredDaily.await()
            if (realtimeResponse.status == "ok" && dailyResponse.status == "ok") {
                val weather = Weather(realtimeResponse.result.realtime, dailyResponse.result.daily)
                Result.success(weather)
            } else {
                Result.failure(
                    RuntimeException(
                        "realtime response status is ${realtimeResponse.status}" +
                                "daily response status is ${dailyResponse.status}"
                    )
                )
            }
        }
    }
    fun savePlace(place: Place) = fire(Dispatchers.IO){
        Result.success(PlaceDao.savePlace(place))
    }
    fun getSavedPlace() = fire(Dispatchers.IO){
        Result.success(PlaceDao.getSavedPlace())
    }
    fun isPlaceSaved() = fire(Dispatchers.IO){
        Result.success(PlaceDao.isPlaceSaved())
    }
//    fun savePlace(place: Place) = PlaceDao.savePlace(place)
//    fun getSavedPlace() = PlaceDao.getSavedPlace()
//    fun isPlaceSaved() = PlaceDao.isPlaceSaved()

    /**
     * 对耗时任务（网络请求、io读写）异常进行统一处理
     */
    private fun <T> fire(context: CoroutineContext, block: suspend () -> Result<T>) =
        liveData<Result<T>>(context) {
            val result = try{
                block()
            }catch (e :Exception){
                Result.failure<T>(e)
            }
            emit(result)
        }



}