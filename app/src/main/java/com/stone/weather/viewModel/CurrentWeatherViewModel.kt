package com.stone.weather.viewModel

import androidx.lifecycle.*
import com.stone.weather.model.CurrentWeatherResponse
import com.stone.weather.model.ForecastWeatherResponse
import com.stone.weather.network.ApiResponse
import com.stone.weather.repository.BaseRepository
import com.stone.weather.repository.CurrentWeatherRepository
import com.stone.weather.repository.ForecastWeatherRepository

class CurrentWeatherViewModel : ViewModel() {
    var apiResponse = MediatorLiveData<ApiResponse<Any>>()

    private val currentWeatherRepository = CurrentWeatherRepository()
    private val forecastWeatherRepository = ForecastWeatherRepository()
    var weatherForecast = MutableLiveData<ForecastWeatherResponse>()



    fun getCurrentWeather(city: String): LiveData<ApiResponse<Any>> {
        apiResponse.addSource(
            currentWeatherRepository.getWeather(city)
        ) { t -> apiResponse.value = t }
        getWeatherForecast(city)
        return apiResponse
    }

    private fun getWeatherForecast(city: String) {
        weatherForecast =
            forecastWeatherRepository.getForecastWeather(city) as MutableLiveData<ForecastWeatherResponse>
    }


}