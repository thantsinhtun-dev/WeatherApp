package com.stone.weather.viewModel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stone.weather.model.CurrentWeatherResponse
import com.stone.weather.network.ApiService
import com.stone.weather.network.RetrofitApi
import com.stone.weather.repository.CurrentWeatherRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CurrentWeatherFragmentVM :ViewModel(){
    private val currentWeatherRepository=CurrentWeatherRepository()
    fun getCurrentWeather(city:String):LiveData<CurrentWeatherResponse>{
        return currentWeatherRepository.getWeatherByCity(city)
    }


}