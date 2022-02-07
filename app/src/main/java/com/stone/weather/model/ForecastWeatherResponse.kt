package com.stone.weather.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForecastWeatherResponse(
    @Json(name = "message")
    val message:String,
    @Json(name = "list")
    val weatherList: List<ForecastListResponse>,
    @Json(name = "city")
    val city:CityResponse

)

@JsonClass(generateAdapter = true)
data class ForecastListResponse(
    @Json(name = "main")
    val main:MainStatusResponse,
    @Json(name = "weather")
    val weather:List<MainWeatherResponse>,
    @Json(name = "wind")
    val wind:WindStatusResponse,
    @Json(name = "dt_txt")
    val date:String
)

@JsonClass(generateAdapter = true)
data class CityResponse(
    @Json(name = "name")
    val cityName:String
)