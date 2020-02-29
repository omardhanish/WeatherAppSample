package com.example.weatherappsample.data.source.remote

import com.example.weatherappsample.data.CurrentWeather
import com.example.weatherappsample.data.WeatherForeCast
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface WeatherApi
{

    @GET("data/2.5/weather?q=Bengaluru&APPID=9b8cb8c7f11c077f8c4e217974d9ee40")
    suspend fun getCurrentTemp() : Response<CurrentWeather>

    @GET("data/2.5/forecast?q=Bengaluru&APPID=9b8cb8c7f11c077f8c4e217974d9ee40")
    suspend fun getForecast() : Response<WeatherForeCast>

}