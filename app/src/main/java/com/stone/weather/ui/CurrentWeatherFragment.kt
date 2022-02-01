package com.stone.weather.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.stone.weather.databinding.FragmentCurrentWeatherBinding

class CurrentWeatherFragment:Fragment() {
    private val binding by lazy {
        FragmentCurrentWeatherBinding.inflate(LayoutInflater.from(this.context))
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}