package com.stone.weather

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("weather")
    fun getWeatherList(
        @Query("zip") zipCode: String,
        ):Call<WeatherResponse>
}