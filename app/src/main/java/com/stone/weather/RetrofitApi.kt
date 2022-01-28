package com.stone.weather

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitApi {
    var retrofit: Retrofit? = null;

    fun instance(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .client(OkHttpClient.Builder().addInterceptor(AppInterceptor()).build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        }
        return retrofit!!
    }
}