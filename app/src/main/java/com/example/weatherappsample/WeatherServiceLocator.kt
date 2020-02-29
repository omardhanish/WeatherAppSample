package com.example.weatherappsample

import android.content.Context
import com.example.weatherappsample.data.source.DefaultWeatherRepository
import com.example.weatherappsample.data.source.WeatherDataSource
import com.example.weatherappsample.data.source.WeatherRepository
import com.example.weatherappsample.data.source.remote.WeatherApi
import com.example.weatherappsample.data.source.remote.WeatherRemoteDataSource
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

object WeatherServiceLocator
{
    @Volatile
    var weatherRepository : WeatherRepository? = null
        set(value) {
            field = value
        }

    fun getAppWeatherRepository() : WeatherRepository
    {
        synchronized(this) {
            return weatherRepository ?: DefaultWeatherRepository(getWeatherRemoteDataSource())
        }
    }

    fun getWeatherRemoteDataSource() : WeatherDataSource
    {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val weatherApi : WeatherApi = retrofit.create(WeatherApi::class.java)

        return WeatherRemoteDataSource(weatherApi)
    }


}