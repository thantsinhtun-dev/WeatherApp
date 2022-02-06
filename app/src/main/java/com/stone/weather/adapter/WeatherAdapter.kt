package com.stone.weather.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stone.weather.databinding.WeatherItemLayoutBinding
import com.stone.weather.model.MainWeatherResponse

class WeatherAdapter(private val list:List<MainWeatherResponse>):RecyclerView.Adapter<WeatherViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        val bindingLayout=WeatherItemLayoutBinding.inflate(layoutInflater,parent,false)
        return WeatherViewHolder(bindingLayout)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.binding.itemWeather.text=list[position].description
    }

    override fun getItemCount(): Int {
       return list.size
    }

}
class WeatherViewHolder(var binding: WeatherItemLayoutBinding) :RecyclerView.ViewHolder(binding.root){}