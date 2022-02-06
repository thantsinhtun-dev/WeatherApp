package com.stone.weather.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitApi {
    var retrofit: Retrofit? = null;

    fun instance(): Retrofit {
        if (retrofit == null) {
            val client=OkHttpClient.Builder().addInterceptor(AppInterceptor())
            val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
            retrofit = Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .client(client.build())
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()

        }
        return retrofit!!
    }
}