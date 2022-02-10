package com.stone.weather

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.stone.weather.databinding.ActivityMainBinding
import com.stone.weather.model.ResponseStatus
import com.stone.weather.ui.CurrentWeatherCondition
import com.stone.weather.ui.CurrentWeatherFragment
import com.stone.weather.ui.ForecastWeatherFragment
import com.stone.weather.viewModel.CurrentWeatherViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: CurrentWeatherViewModel by viewModels<CurrentWeatherViewModel>()
    private val fragmentIdCurrentWeatherFragment = R.id.currentWeatherFragment
    private val fragmentIdForecastWeatherFragment = R.id.forecastWeatherFragment
    private val fragmentIdCurrentWeatherCondition = R.id.currentWeatherConditionFragment
    private var isAvailableData=false


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        changeFrameLayout(fragmentIdCurrentWeatherFragment, CurrentWeatherFragment())
        changeFrameLayout(fragmentIdForecastWeatherFragment, ForecastWeatherFragment())
        changeFrameLayout(fragmentIdCurrentWeatherCondition, CurrentWeatherCondition())
        showLoading()
            viewModel.getCurrentWeather("Yangon").observe(this@MainActivity) {

                when (it.status) {
                    ResponseStatus.SUCCESS -> {
                        showData()
                        isAvailableData=true
                        Toast.makeText(applicationContext, "success", Toast.LENGTH_SHORT).show()
                        Log.i("MainLog", "success")
                    }
                    ResponseStatus.ERROR -> {

                        Toast.makeText(applicationContext, it.message, Toast.LENGTH_LONG).show()
                        Log.i("MainLog", "error")
                    }
                    ResponseStatus.NETWORK_ERROR->{
                        if (isAvailableData){
                            Toast.makeText(applicationContext, "Check your connection \n try again", Toast.LENGTH_LONG).show()
                            showData()
                        }else {
                            showNetwork()
                            Toast.makeText(applicationContext, it.message, Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            }




        binding.btnTryAgain.setOnClickListener {
            showLoading()
            viewModel.getCurrentWeather("Yangon")
        }


        binding.btnSearch.setOnClickListener {
            binding.toolbar.visibility = View.GONE
            binding.toolbarSearchView.visibility = View.VISIBLE
            binding.searchView.requestFocus()
        }
        binding.btnClose.setOnClickListener {
            binding.searchView.clearFocus()
            binding.searchView.setQuery("", false)
            binding.toolbarSearchView.visibility = View.GONE
            binding.toolbar.visibility = View.VISIBLE
        }
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {


                viewModel.getCurrentWeather(query!!)
                binding.searchView.clearFocus()
                showLoading()

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })


    }


    private fun showLoading() {
        binding.currentWeatherFragment.visibility = View.GONE
        binding.failLayout.visibility=View.GONE
        binding.currentWeatherConditionFragment.visibility = View.GONE
        binding.forecastWeatherFragment.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE

    }
    private fun showNetwork(){

        binding.currentWeatherFragment.visibility = View.GONE
        binding.currentWeatherConditionFragment.visibility = View.GONE
        binding.forecastWeatherFragment.visibility = View.GONE
        binding.progressBar.visibility = View.GONE
        binding.failLayout.visibility=View.VISIBLE
    }

    private fun showData() {
        binding.progressBar.visibility = View.GONE
        binding.failLayout.visibility=View.GONE
        binding.currentWeatherFragment.visibility = View.VISIBLE
        binding.currentWeatherConditionFragment.visibility = View.VISIBLE
        binding.forecastWeatherFragment.visibility = View.VISIBLE
    }



    private fun changeFrameLayout(fragmentId: Int, fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(fragmentId, fragment)
            .commit()
    }


}