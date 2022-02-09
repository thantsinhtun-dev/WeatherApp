package com.stone.weather

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.activityViewModels
import android.view.View
import android.view.ViewTreeObserver
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.stone.weather.databinding.ActivityMainBinding
import com.stone.weather.model.ResponseStatus
import com.stone.weather.network.ApiResponse
import com.stone.weather.network.RetrofitApi
import com.stone.weather.ui.CurrentWeatherCondition
import com.stone.weather.ui.CurrentWeatherFragment
import com.stone.weather.ui.ForecastWeatherFragment
import com.stone.weather.viewModel.CurrentWeatherViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel:CurrentWeatherViewModel by viewModels<CurrentWeatherViewModel>()
    private val fragmentIdCurrentWeatherFragment = R.id.currentWeatherFragment
    private val fragmentIdForecastWeatherFragment = R.id.forecastWeatherFragment
    private val fragmentIdCurrentWeatherCondition = R.id.currentWeatherConditionFragment


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        val content: View = findViewById(android.R.id.content)
        content.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    // Check if the initial data is ready.
                    return if (viewModel.isReady()) {
                        // The content is ready; start drawing.
                        Log.i("Content", "contentReady")
                        content.viewTreeObserver.removeOnPreDrawListener(this)
                        true
                    } else {
                        // The content is not ready; suspend.
                        Log.i("Content", "contentNotReady")
                        false
                    }
                }
            }
        )



        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        //binding.toolbar.inflateMenu(R.menu.main_menu)




        viewModel.getCurrentWeather("Yangon").observe(this@MainActivity){
            when(it.status){
                ResponseStatus.SUCCESS-> {
                    Toast.makeText(applicationContext,"success",Toast.LENGTH_SHORT).show()
                    Log.i("MainLog","success")
                }
                ResponseStatus.ERROR-> {
                    Toast.makeText(applicationContext,it.message,Toast.LENGTH_LONG).show()
                    Log.i("MainLog","error")
                }
            }
        }
        changeFrameLayout(fragmentIdCurrentWeatherFragment, CurrentWeatherFragment())
        changeFrameLayout(fragmentIdForecastWeatherFragment, ForecastWeatherFragment())
        changeFrameLayout(fragmentIdCurrentWeatherCondition, CurrentWeatherCondition())

//        runOnUiThread {
//            val geoCoder = Geocoder(this, Locale.getDefault())
//            val addresses: List<Address> = geoCoder.getFromLocation(18.9333, 96.4333, 1)
//
//            val result = addresses[0].locality
//            try {
//
//
//                Log.i("MainLocation", result)
//            }catch (e:NullPointerException) {
//                Toast.makeText(applicationContext, result, Toast.LENGTH_SHORT).show()
//            }
//        }


        binding.btnSearch.setOnClickListener{
            binding.toolbar.visibility=View.GONE
            binding.toolbarSearchView.visibility=View.VISIBLE
            binding.searchView.requestFocus()
        }
        binding.btnClose.setOnClickListener {
            binding.searchView.clearFocus()
            binding.searchView.setQuery("",false)
            binding.toolbarSearchView.visibility=View.GONE
            binding.toolbar.visibility=View.VISIBLE
        }
        binding.searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {


                viewModel.getCurrentWeather(query!!)
                binding.searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })



    }




    private fun changeFrameLayout(fragmentId: Int, fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(fragmentId, fragment)
            .commit()
    }

//    private fun executeNetworkCall(zip: String) {
//        val api=retrofit.create(ApiService::class.java)
//        api.getWeatherList(zip).enqueue(object : Callback<WeatherResponse>{
//                override fun onResponse(
//                    call: Call<WeatherResponse>,
//                    response: Response<WeatherResponse>
//                ) {
//                    if (response.isSuccessful){
//                        Toast.makeText(applicationContext,"Success",Toast.LENGTH_SHORT).show()
//                        textview.text=response.body()?.cityName.toString()
//                    }else{
//                        Toast.makeText(applicationContext,"fail",Toast.LENGTH_SHORT).show()
//
//                    }
//
//                }
//
//                override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
//                    Log.i("mainLog",t.message.toString())
//                    Toast.makeText(applicationContext,"fail",Toast.LENGTH_SHORT).show()
//
//                }
//            })
//    }
}