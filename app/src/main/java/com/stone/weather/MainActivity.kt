package com.stone.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.stone.weather.databinding.ActivityMainBinding
import com.stone.weather.ui.CurrentWeatherCondition
import com.stone.weather.ui.CurrentWeatherFragment
import com.stone.weather.ui.ForecastWeatherFragment

class MainActivity : AppCompatActivity() {
    //    private val textview by lazy {
//        findViewById<TextView>(R.id.textview)
//    }
//    private val editText by lazy {
//        findViewById<EditText>(R.id.edit)
//    }
//    private val button by lazy {
//        findViewById<Button>(R.id.btn)
//    }
//    private val retrofit by lazy {
//        RetrofitApi().instance()
//    }
    private val binding by lazy {
        ActivityMainBinding.inflate(LayoutInflater.from(this))
    }
    private val fragmentIdCurrentWeatherFragment = R.id.currentWeatherFragment
    private val fragmentIdForecastWeatherFragment = R.id.forecastWeatherFragment
    private val fragmentIdCurrentWeatherCondition = R.id.currentWeatherConditionFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
        //binding.toolbar.inflateMenu(R.menu.main_menu)

        changeFrameLayout(fragmentIdCurrentWeatherFragment, CurrentWeatherFragment())
        changeFrameLayout(fragmentIdForecastWeatherFragment, ForecastWeatherFragment())
        changeFrameLayout(fragmentIdCurrentWeatherCondition, CurrentWeatherCondition())


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
                Toast.makeText(applicationContext,query,Toast.LENGTH_SHORT).show()
                binding.searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })


//        button.setOnClickListener {
//            val zip=editText.text.toString()
//            executeNetworkCall(zip)
//        }
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