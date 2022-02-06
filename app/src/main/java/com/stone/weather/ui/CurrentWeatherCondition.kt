package com.stone.weather.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.stone.weather.R
import com.stone.weather.databinding.FragmentCurrentWeatherConditionBinding
import com.stone.weather.viewModel.CurrentWeatherFragmentVM


class CurrentWeatherCondition:Fragment() {
private lateinit var binding:FragmentCurrentWeatherConditionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DataBindingUtil.inflate(inflater, R.layout.fragment_current_weather_condition,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewM=CurrentWeatherFragmentVM()
        viewM.
    }
}