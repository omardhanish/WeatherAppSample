package com.example.weatherappsample

import android.app.Application
import com.example.weatherappsample.data.source.WeatherRepository

class WeatherApplication : Application()
{

    val weatherRepository : WeatherRepository
        get() = WeatherServiceLocator.getAppWeatherRepository()

    companion object {
        @JvmStatic lateinit var instance: WeatherApplication
    }

    override fun onCreate() {
        super.onCreate()

        instance = this

    }

}