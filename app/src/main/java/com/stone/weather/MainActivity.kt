package com.stone.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val textview by lazy {
        findViewById<TextView>(R.id.textview)
    }
    private val editText by lazy {
        findViewById<EditText>(R.id.edit)
    }
    private val button by lazy {
        findViewById<Button>(R.id.btn)
    }
    private val retrofit by lazy {
        RetrofitApi().instance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            val zip=editText.text.toString()
            executeNetworkCall(zip)
        }
    }

    private fun executeNetworkCall(zip: String) {
        val api=retrofit.create(ApiService::class.java)
        api.getWeatherList(zip,"d5d1e21a7fc1077d18ef2d6b8b694b93").enqueue(object : Callback<WeatherResponse>{
                override fun onResponse(
                    call: Call<WeatherResponse>,
                    response: Response<WeatherResponse>
                ) {
                    if (response.isSuccessful){
                        Toast.makeText(applicationContext,"Success",Toast.LENGTH_SHORT).show()
                        textview.text=response.body()?.cityName.toString()
                    }

                }

                override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                    Log.i("main",t.message.toString())
                    Toast.makeText(applicationContext,"fail",Toast.LENGTH_SHORT).show()

                }
            })
    }
}