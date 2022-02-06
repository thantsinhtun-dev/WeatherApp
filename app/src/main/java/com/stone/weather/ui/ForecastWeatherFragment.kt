package com.stone.weather.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.stone.weather.adapter.WeatherAdapter
import com.stone.weather.databinding.FragmentForecastWeatherBinding
import com.stone.weather.model.CurrentWeatherResponse
import com.stone.weather.model.MainWeatherResponse

class ForecastWeatherFragment:BaseFragment() {
    private val binding by lazy {
        FragmentForecastWeatherBinding.inflate(LayoutInflater.from(this.context))
    }
    lateinit var adapter:WeatherAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        var list= ArrayList<MainWeatherResponse>()
        viewModel.weatherForecast.observe(viewLifecycleOwner){
            list= it.weather as ArrayList<MainWeatherResponse>
            Toast.makeText(context, it.toString(), Toast.LENGTH_LONG).show()
        }
        adapter= WeatherAdapter(list)

        binding.recycler.adapter=adapter

    }
}