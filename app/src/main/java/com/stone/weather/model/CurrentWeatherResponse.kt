package com.stone.weather.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CurrentWeatherResponse(
    @Json(name = "coord") val location: LocationResponse,
    @Json(name = "weather") val weather: List<MainWeatherResponse>,
    @Json(name = "main") val mainStatus: MainStatusResponse,
    @Json(name = "wind") val wind:WindStatusResponse,
    @Json(name = "name") val name: String
)

@JsonClass(generateAdapter = true)
data class LocationResponse(

    @Json(name = "lat")  val lat: String,
    @Json(name = "lon") val lon: String
)

@JsonClass(generateAdapter = true)
data class MainWeatherResponse(
    @Json(name = "description") val description: String,
    @Json(name = "icon") val icon: String
)

@JsonClass(generateAdapter = true)
data class MainStatusResponse(
    @Json(name = "temp") val temp: String,
    @Json(name = "feels_like") val feelLike: String,
    @Json(name = "pressure") val pressure: String,
    @Json(name = "humidity") val humidity: String
)

@JsonClass(generateAdapter = true)
data class WindStatusResponse(
    @Json(name = "speed") val speed:String
)