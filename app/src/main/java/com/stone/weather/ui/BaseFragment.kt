package com.stone.weather.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.stone.weather.viewModel.CurrentWeatherViewModel

open class BaseFragment:Fragment() {
    val viewModel: CurrentWeatherViewModel by activityViewModels()

}