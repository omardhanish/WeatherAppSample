package com.example.weatherappsample.data.source

import androidx.lifecycle.LiveData
import com.example.weatherappsample.data.CurrentWeather
import com.example.weatherappsample.data.Result
import com.example.weatherappsample.data.WeatherForeCast

interface WeatherRepository
{

    suspend fun getCurrentWeather() : LiveData<Result<CurrentWeather>>

    suspend fun getForeCastWeather() : LiveData<Result<WeatherForeCast>>

}