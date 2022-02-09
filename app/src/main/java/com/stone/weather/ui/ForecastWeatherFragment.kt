package com.stone.weather.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.stone.weather.R
import com.stone.weather.adapter.WeatherAdapter
import com.stone.weather.databinding.FragmentForecastWeatherBinding
import com.stone.weather.model.CurrentWeatherResponse
import com.stone.weather.model.ForecastListResponse
import com.stone.weather.model.MainWeatherResponse
import okhttp3.internal.notify
import okhttp3.internal.notifyAll

class ForecastWeatherFragment:BaseFragment() {
    private lateinit var binding:FragmentForecastWeatherBinding
    private val adapter=WeatherAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DataBindingUtil.inflate(inflater, R.layout.fragment_forecast_weather,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.weatherForecast.observe(viewLifecycleOwner){

            adapter.setWeatherList(it.weatherList)
            viewModel.forecastReady=true
            Log.i("Content","Forecast is ready")

        }




        binding.recycler.setHasFixedSize(true)
        binding.recycler.adapter=adapter

    }
}