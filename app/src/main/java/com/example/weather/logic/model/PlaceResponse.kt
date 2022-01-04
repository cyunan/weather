package com.example.weather.logic.model

import com.google.gson.annotations.SerializedName

/**
 * Author by CYN, Date on 2021/12/29
 * 地区接口所返回的JSON数据
 */

data class PlaceResponse(val status: String, val places: List<Place>)
data class Place(val name: String, val location: Location,
                 @SerializedName("formatted_address") val address: String)
data class Location(val lng: String, val lat: String)