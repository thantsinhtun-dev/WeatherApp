package com.stone.weather.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.stone.weather.model.CurrentWeatherResponse
import com.stone.weather.network.ApiResponse
import com.stone.weather.network.ApiService
import com.stone.weather.network.RetrofitApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CurrentWeatherRepository :BaseRepository(){

    private val tempData=MutableLiveData<CurrentWeatherResponse>()

    fun getWeatherByCity(city:String):LiveData<CurrentWeatherResponse>{

        apiService.getCurrentWeatherByCityName(city)
            .enqueue(object : Callback<CurrentWeatherResponse>{
                override fun onResponse(
                    call: Call<CurrentWeatherResponse>,
                    response: Response<CurrentWeatherResponse>
                ) {
                    if (response.isSuccessful){
                        response.body()?.let {
                            tempData.value=it

                        }
                        Log.i("CurrentWeatherRepository","successful")
                    }else{
                        Log.i("CurrentWeatherRepository","not successful")
                    }

                }

                override fun onFailure(call: Call<CurrentWeatherResponse>, t: Throwable) {
                    Log.i("CurrentWeatherRepository",t.message.toString())
                }
            })
        return tempData
    }
    fun getWeather(city:String):LiveData<ApiResponse<Any>>{
        var result=MutableLiveData<ApiResponse<Any>>()
        apiService.getCurrentWeatherByCityName(city)
            .enqueue(object : Callback<CurrentWeatherResponse>{
                override fun onResponse(
                    call: Call<CurrentWeatherResponse>,
                    response: Response<CurrentWeatherResponse>
                ) {
                    if (response.isSuccessful){
                        response.body()?.let {
                            //tempData.value=it
                            result.postValue(ApiResponse.Success(
                                it
                            ))
                        }
                        Log.i("CurrentWeatherRepository","successful")
                    }else{
                        result.postValue(ApiResponse.Error(
                            "City Not Found"
                        ))
                        Log.i("CurrentWeatherRepository","not successful")
                    }

                }

                override fun onFailure(call: Call<CurrentWeatherResponse>, t: Throwable) {
                    Log.i("CurrentWeatherRepository",t.message.toString())
                }
            })
        return result
    }
}