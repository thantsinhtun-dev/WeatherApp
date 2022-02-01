package com.stone.weather.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.stone.weather.databinding.FragmentCurrentWeatherConditionBinding


class CurrentWeatherCondition:Fragment() {
    private val binding by lazy {
        FragmentCurrentWeatherConditionBinding.inflate(LayoutInflater.from(this.context))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }
}