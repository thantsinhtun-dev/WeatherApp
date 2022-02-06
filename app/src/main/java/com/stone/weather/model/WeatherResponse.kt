package com.stone.weather.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class WeatherResponse(
//    @Expose
//    val icon:String,
//    @Expose
//    @SerializedName("temperature")
//    val temp:String,
    @Expose
    @SerializedName("name")
    val cityName:String
)