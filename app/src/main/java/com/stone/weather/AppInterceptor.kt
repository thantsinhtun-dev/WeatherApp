package com.stone.weather

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AppInterceptor :Interceptor{
    companion object{
        private const val API_KEY = "d5d1e21a7fc1077d18ef2d6b8b694b93"
        private const val UNIT="metric"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val url=chain.request().url()
        val newUrl=url.newBuilder()
            .addQueryParameter("appid", API_KEY)
            .addQueryParameter("units", UNIT)
            .build()
        return chain.proceed(Request.Builder().url(newUrl).build())
    }

}