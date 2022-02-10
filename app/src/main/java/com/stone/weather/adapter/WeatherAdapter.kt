package com.stone.weather.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.stone.weather.R
import com.stone.weather.databinding.WeatherItemLayoutBinding
import com.stone.weather.model.ForecastListResponse
import com.stone.weather.model.MainWeatherResponse

class WeatherAdapter(private val listener:OnItemClickListener):RecyclerView.Adapter<WeatherViewHolder>() {

    private var list:List<ForecastListResponse> = emptyList()

    fun setWeatherList(weatherList:List<ForecastListResponse>){
        list = weatherList ?: emptyList()
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        val binding:WeatherItemLayoutBinding=DataBindingUtil.inflate(layoutInflater, R.layout.weather_item_layout,parent,false)
        return WeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
       // holder.binding.itemWeather.text= list[position]
        holder.bind(list[position],listener)
    }

    override fun getItemCount(): Int {
       return list.size

    }

}
class WeatherViewHolder(var binding: WeatherItemLayoutBinding) :RecyclerView.ViewHolder(binding.root){
    fun bind(forecastWeather:ForecastListResponse,listener: OnItemClickListener){
        binding.weatherIcon=forecastWeather.weather[0].icon
        binding.temperature=forecastWeather.main.temp
        binding.time=forecastWeather.date
        binding.itemWeatherIcon.setOnClickListener {
            listener.onItemClick(forecastWeather.weather[0].description)
        }


    }
}