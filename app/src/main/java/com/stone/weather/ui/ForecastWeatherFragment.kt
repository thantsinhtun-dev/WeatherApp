package com.stone.weather.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.stone.weather.adapter.WeatherAdapter
import com.stone.weather.databinding.FragmentForecastWeatherBinding

class ForecastWeatherFragment:Fragment() {
    private val binding by lazy {
        FragmentForecastWeatherBinding.inflate(LayoutInflater.from(this.context))
    }
    val adapter=WeatherAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val str = ArrayList<String>();
        str.add("helo")
        str.add("helo2")
        str.add("helo3")
        str.add("helo4")
        str.add("helo5")
        adapter.list.addAll(
            str
        )
        binding.recycler.adapter=adapter

    }
}