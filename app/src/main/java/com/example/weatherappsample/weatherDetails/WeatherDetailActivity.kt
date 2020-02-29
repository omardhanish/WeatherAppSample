package com.example.weatherappsample.weatherDetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.weatherappsample.R
import com.example.weatherappsample.WeatherApplication

class WeatherDetailActivity : AppCompatActivity()
{

    val weatherDetailViewModel : WeatherDetailViewModel by lazy{
        ViewModelProviders.of(this, WeatherDetailViewModelFactory((this.applicationContext as WeatherApplication).weatherRepository))
            .get(WeatherDetailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        weatherDetailViewModel.checkApiLogSample()

    }

}