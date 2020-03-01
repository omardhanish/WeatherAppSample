package com.example.weatherappsample.data.source

import androidx.lifecycle.MutableLiveData
import com.example.weatherappsample.data.CurrentWeather
import com.example.weatherappsample.data.Result
import com.example.weatherappsample.data.WeatherForeCast
import retrofit2.Response

interface WeatherDataSource
{

    suspend fun getCurrentWeather() : Result<CurrentWeather>

    suspend fun getForeCastWeather() : Result<WeatherForeCast>

}