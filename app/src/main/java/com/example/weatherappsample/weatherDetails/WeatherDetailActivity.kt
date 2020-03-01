package com.example.weatherappsample.weatherDetails

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherappsample.R
import com.example.weatherappsample.Util.CommonUtils
import com.example.weatherappsample.WeatherApplication
import com.example.weatherappsample.WeatherServiceLocator
import com.example.weatherappsample.data.CurrentWeather
import com.example.weatherappsample.data.WeatherForeCast
import com.google.android.material.button.MaterialButton

class WeatherDetailActivity : AppCompatActivity()
{

    private val weatherDetailViewModel : WeatherDetailViewModel by lazy{
        ViewModelProviders.of(this,
            WeatherDetailViewModelFactory((this.applicationContext as WeatherApplication).weatherRepository))
            .get(WeatherDetailViewModel::class.java)
    }

    //view
    private lateinit var tvTemp : TextView
    private lateinit var tvLocation : TextView
    private lateinit var cvForecastParent : CardView
    private lateinit var rvForecastWeather : RecyclerView
    private lateinit var inLoader : View
    private lateinit var inSomethingWentWrong : View
    private lateinit var ivLoader : ImageView
    private lateinit var btRetry : Button
    private lateinit var coordinatorLayout: CoordinatorLayout


    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var weatherDetailsAdapter: WeatherDetailsAdapter

    private val rotationAnim : Animation by lazy {
        AnimationUtils.loadAnimation(this, R.anim.anim_rotation);
    }

    private val slideToTopAnim : Animation by lazy {
        AnimationUtils.loadAnimation(this, R.anim.ainm_bottom_to_top);
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        initObservers()

        linearLayoutManager = LinearLayoutManager(this , RecyclerView.VERTICAL , false)
        rvForecastWeather.layoutManager = linearLayoutManager

        weatherDetailsAdapter = WeatherDetailsAdapter(this , weatherDetailViewModel.forecastWeatherList)
        rvForecastWeather.adapter = weatherDetailsAdapter

        btRetry.setOnClickListener {
            weatherDetailViewModel.callWeatherApi()
        }

        weatherDetailViewModel.callWeatherApi()
    }

    private fun initViews()
    {
        tvTemp = findViewById(R.id.tv_temp)
        tvLocation = findViewById(R.id.tv_location)
        cvForecastParent = findViewById(R.id.cv_forecast_parent)
        rvForecastWeather = findViewById(R.id.rv_forecast_weather)
        inLoader = findViewById(R.id.in_loader)
        inSomethingWentWrong = findViewById(R.id.in_something_went_wrong)
        ivLoader = findViewById(R.id.iv_loader)
        btRetry = findViewById(R.id.bt_retry)
        coordinatorLayout = findViewById(R.id.coordinator)
    }

    private fun initObservers()
    {
        weatherDetailViewModel.currentWeather.observe(this , Observer {
            setUICurrentWeather(it)
        })

        weatherDetailViewModel.foreCastWeather.observe(this , Observer {
            setUiForeCastWeather(it)
        })

        weatherDetailViewModel.dataLoading.observe(this , Observer {
            loaderUi(it)
        })

        weatherDetailViewModel.error.observe(this , Observer {
            errorUi(it)
        })
    }

    private fun setUICurrentWeather(currentWeatherData : CurrentWeather){
        tvLocation.text = currentWeatherData.name
        tvTemp.text = CommonUtils.convertKelvinToCelsius(currentWeatherData.main?.temp)
    }

    private fun setUiForeCastWeather(forecastWeather : WeatherForeCast){
        weatherDetailViewModel.forecastWeatherList.clear()
        weatherDetailViewModel.forecastWeatherList.addAll(forecastWeather.list)
        weatherDetailsAdapter.notifyDataSetChanged()

        cvForecastParent.startAnimation(slideToTopAnim)
    }

    private fun loaderUi(show: Boolean)
    {
        if(show){
            inLoader.visibility = View.VISIBLE
            inSomethingWentWrong.visibility = View.INVISIBLE
            coordinatorLayout.visibility = View.INVISIBLE

            ivLoader.startAnimation(rotationAnim);
        }else{
            inLoader.visibility = View.INVISIBLE
            inSomethingWentWrong.visibility = View.INVISIBLE
            coordinatorLayout.visibility = View.VISIBLE

            ivLoader.clearAnimation()
        }
    }

    private fun errorUi(error: Boolean)
    {
        if(error){
            inLoader.visibility = View.INVISIBLE
            inSomethingWentWrong.visibility = View.VISIBLE
            coordinatorLayout.visibility = View.INVISIBLE
        }else{
            inLoader.visibility = View.INVISIBLE
            inSomethingWentWrong.visibility = View.INVISIBLE
            coordinatorLayout.visibility = View.VISIBLE
        }
    }

}