package com.stone.weather.repository

import com.stone.weather.network.ApiService
import com.stone.weather.network.RetrofitApi
import retrofit2.Retrofit

open class BaseRepository {
    private var repository:BaseRepository? =null
     var apiService: ApiService =RetrofitApi().instance().create(ApiService::class.java)
    fun getInstance():BaseRepository{
        if (repository==null){
            repository=BaseRepository()

        }
        return repository!!
    }

}