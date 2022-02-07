package com.stone.weather.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stone.weather.model.CurrentWeatherResponse
import com.stone.weather.model.ForecastWeatherResponse
import com.stone.weather.repository.CurrentWeatherRepository
import com.stone.weather.repository.ForecastWeatherRepository

class CurrentWeatherViewModel :ViewModel(){

    private val currentWeatherRepository=CurrentWeatherRepository()
    private val forecastWeatherRepository=ForecastWeatherRepository()
    var currentWeatherResponse = MutableLiveData<CurrentWeatherResponse>()
    var weatherForecast=MutableLiveData<ForecastWeatherResponse>()
    fun getCurrentWeather(city:String){
        weatherForecast=forecastWeatherRepository.getForecastWeather(city) as MutableLiveData<ForecastWeatherResponse>
        currentWeatherResponse= currentWeatherRepository.getWeatherByCity(city) as MutableLiveData<CurrentWeatherResponse>;

    }


}