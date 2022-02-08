package com.stone.weather

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.stone.weather.viewModel.CurrentWeatherViewModel

class ActivitySplash : AppCompatActivity() {
    private val viewModel: CurrentWeatherViewModel by viewModels<CurrentWeatherViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {

        // val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)




        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}