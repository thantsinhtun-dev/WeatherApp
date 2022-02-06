package com.stone.weather.network

import com.stone.weather.model.CurrentWeatherResponse
import com.stone.weather.model.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("weather")
    fun getWeatherList(
        @Query("zip") zipCode: String,
    ): Call<WeatherResponse>

    @GET("weather")
    fun getCurrentWeatherByCityName(
        @Query("q") cityName: String
    ): Call<CurrentWeatherResponse>

    @GET("forecast")
    fun getForecastWeatherByCityName(
        @Query("q") cityName: String
    ): Call<CurrentWeatherResponse>

}