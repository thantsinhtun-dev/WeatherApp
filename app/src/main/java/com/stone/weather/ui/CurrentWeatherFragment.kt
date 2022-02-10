package com.stone.weather.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.stone.weather.R
import com.stone.weather.databinding.FragmentCurrentWeatherBinding
import com.stone.weather.model.CurrentWeatherResponse
import com.stone.weather.model.ResponseStatus
import com.stone.weather.utils.AppUtils
import com.stone.weather.viewModel.CurrentWeatherViewModel

class CurrentWeatherFragment : BaseFragment() {

    private lateinit var binding: FragmentCurrentWeatherBinding
    private var weatherDescription=""


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_current_weather, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        viewModel.apiResponse.observe(this.viewLifecycleOwner) {response->
            if (response.status.equals(ResponseStatus.SUCCESS)) {

                val it = response.data as CurrentWeatherResponse
                binding.temperature = it.mainStatus.temp
                binding.city = it.name
                binding.weatherIcon = it.weather[0].icon
                weatherDescription=it.weather[0].description


                Log.i("Content", "Current Weather is ready")
            }

            
        }
        binding.currentDate.text=AppUtils.getCurrentDay()
        binding.currentWeatherImg.setOnClickListener {
            Toast.makeText(context,weatherDescription,Toast.LENGTH_SHORT).show()
        }
        binding.currentWeatherImg.setOnLongClickListener {
            Toast.makeText(context,weatherDescription,Toast.LENGTH_LONG).show()
            return@setOnLongClickListener true
        }



    }



}