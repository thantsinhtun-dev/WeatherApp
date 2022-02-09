package com.stone.weather.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.stone.weather.model.CurrentWeatherResponse
import com.stone.weather.model.ForecastListResponse
import com.stone.weather.model.ForecastWeatherResponse
import com.stone.weather.network.ApiResponse
import com.stone.weather.network.ApiService
import com.stone.weather.network.RetrofitApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForecastWeatherRepository :BaseRepository(){

    private val tempData= MutableLiveData<ForecastWeatherResponse>()
    fun getForecastWeather(city:String): LiveData<ForecastWeatherResponse> {
        apiService.getForecastWeatherByCityName(city)
            .enqueue(object : Callback<ForecastWeatherResponse> {
                override fun onResponse(
                    call: Call<ForecastWeatherResponse>,
                    response: Response<ForecastWeatherResponse>
                ) {
                    if (response.isSuccessful){
                        response.body()?.let {
                            tempData.value=it
                        }
                    }
                    Log.i("ForecastWeatherRepository","successful")

                }

                override fun onFailure(call: Call<ForecastWeatherResponse>, t: Throwable) {
                    Log.i("ForecastWeatherRepository",t.message.toString())
                }
            })
        return tempData
    }

//    fun getWeatherForecast(city: String):LiveData<ApiResponse<Any>>{
//        apiService.getForecastWeatherByCityName(city)
//            .enqueue(object :Callback<ForecastWeatherResponse>{
//                override fun onResponse(
//                    call: Call<ForecastWeatherResponse>,
//                    response: Response<ForecastWeatherResponse>
//                ) {
//
//                }
//
//                override fun onFailure(call: Call<ForecastWeatherResponse>, t: Throwable) {
//                    TODO("Not yet implemented")
//                }
//            })
//    }
}