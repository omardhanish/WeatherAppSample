package com.example.weatherappsample.data

import androidx.lifecycle.MutableLiveData
import com.example.weatherappsample.data.source.WeatherDataSource
import java.lang.Exception

class FakeRemoteWeatherDataSource : WeatherDataSource
{
    enum class Response
    {
        SUCCESS,
        FAILURE
    }

    lateinit var response : Response

    override suspend fun getCurrentWeather(): Result<CurrentWeather> {
        if(response == Response.SUCCESS){
            return Result.Success(CurrentWeather());
        }else{
            return Result.Error(Exception("something went wrong"))
        }
    }

    override suspend fun getForeCastWeather(): Result<WeatherForeCast> {

        if(response == Response.SUCCESS){
            return Result.Success(WeatherForeCast());
        }else{
            return Result.Error(Exception("something went wrong"))
        }

    }

}