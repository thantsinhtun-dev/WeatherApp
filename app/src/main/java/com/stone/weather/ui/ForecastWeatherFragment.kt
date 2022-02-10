package com.stone.weather.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.stone.weather.R
import com.stone.weather.adapter.OnItemClickListener
import com.stone.weather.adapter.WeatherAdapter
import com.stone.weather.databinding.FragmentForecastWeatherBinding
import com.stone.weather.model.CurrentWeatherResponse
import com.stone.weather.model.ForecastListResponse
import com.stone.weather.model.MainWeatherResponse
import com.stone.weather.utils.AppUtils
import okhttp3.internal.notify
import okhttp3.internal.notifyAll
import java.util.*
import kotlin.collections.ArrayList

class ForecastWeatherFragment : BaseFragment(),OnItemClickListener {
    private lateinit var binding: FragmentForecastWeatherBinding
    private val adapter = WeatherAdapter(this)
    private var todayList:MutableList<ForecastListResponse> = mutableListOf()
    private var tomorrowList:MutableList<ForecastListResponse> = mutableListOf()
    private var nexThreeDayList:MutableList<ForecastListResponse> = mutableListOf()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_forecast_weather, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.weatherForecast.observe(viewLifecycleOwner) {

            prepareList(it.weatherList)
            adapter.setWeatherList(todayList)
            Log.i("Content", "Forecast is ready")

        }

        binding.recycler.setHasFixedSize(true)
        binding.recycler.adapter = adapter

        binding.todayForecast.setOnClickListener {
            binding.todayForecast.setTextColor(ContextCompat.getColor(this.requireContext(),R.color.yellow))
            binding.tomorrowForecast.setTextColor(ContextCompat.getColor(this.requireContext(),R.color.textColor))
            binding.next3dayForecast.setTextColor(ContextCompat.getColor(this.requireContext(),R.color.textColor))
            adapter.setWeatherList(todayList)
        }
        binding.tomorrowForecast.setOnClickListener {
            binding.todayForecast.setTextColor(ContextCompat.getColor(this.requireContext(),R.color.textColor))
            binding.tomorrowForecast.setTextColor(ContextCompat.getColor(this.requireContext(),R.color.yellow))
            binding.next3dayForecast.setTextColor(ContextCompat.getColor(this.requireContext(),R.color.textColor))
            adapter.setWeatherList(tomorrowList)
        }
        binding.next3dayForecast.setOnClickListener {
            binding.todayForecast.setTextColor(ContextCompat.getColor(this.requireContext(),R.color.textColor))
            binding.tomorrowForecast.setTextColor(ContextCompat.getColor(this.requireContext(),R.color.textColor))
            binding.next3dayForecast.setTextColor(ContextCompat.getColor(this.requireContext(),R.color.yellow))
            adapter.setWeatherList(nexThreeDayList)
        }

    }
    private fun prepareList(list:List<ForecastListResponse>){
        for((index,item) in list.withIndex()){

            when{
                AppUtils.checkDate(item.date,0)->todayList.add(list[index])
                AppUtils.checkDate(item.date,1)->tomorrowList.add(list[index])
                AppUtils.checkDate(item.date,2)->nexThreeDayList.add(list[index])
                AppUtils.checkDate(item.date,3)->nexThreeDayList.add(list[index])
                AppUtils.checkDate(item.date,4)->nexThreeDayList.add(list[index])
            }


        }
    }

    override fun onItemClick(description: String) {
        Toast.makeText(requireContext(),description,Toast.LENGTH_SHORT).show()
    }
}