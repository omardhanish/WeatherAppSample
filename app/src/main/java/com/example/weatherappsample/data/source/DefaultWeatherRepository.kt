package com.example.weatherappsample.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weatherappsample.data.CurrentWeather
import com.example.weatherappsample.data.Result
import com.example.weatherappsample.data.WeatherForeCast

class DefaultWeatherRepository(
    private val weatherDataSource: WeatherDataSource
) : WeatherRepository
{

    override suspend fun getCurrentWeather(): Result<CurrentWeather> {
        return weatherDataSource.getCurrentWeather()
    }

    override suspend fun getForeCastWeather(): Result<WeatherForeCast> {
        return weatherDataSource.getForeCastWeather()
    }

}