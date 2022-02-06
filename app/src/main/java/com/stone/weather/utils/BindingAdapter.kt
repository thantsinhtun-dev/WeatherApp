package com.stone.weather.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import com.stone.weather.R

class BindingAdapter {
    companion object {
        @JvmStatic
        @BindingAdapter("app:ImageUrl", "app:error")
        fun setImage(imageView: ImageView, url: String?, error: Drawable?) {

            url?.let {
                Picasso.get().load("http://openweathermap.org/img/wn/$url@2x.png")
                    .error(R.drawable.ic_cloud_circle_24)
                    .fit()
                    .into(imageView)
            }
        }

        @JvmStatic
        @BindingAdapter("app:setTemp")
        fun prepareTemperature(view:TextView,string: String?){
            val str = string?.substringBefore(".")+ "°C"
            view.text=str
        }
    }
}