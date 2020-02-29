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

    override suspend fun getCurrentWeather(): MutableLiveData<Result<CurrentWeather>> {
        val liveData = MutableLiveData<Result<CurrentWeather>>()

        if(response == Response.SUCCESS){
            liveData.value = Result.Success(CurrentWeather());
        }else{
            liveData.value = Result.Error(Exception("something went wrong"))
        }

        return liveData
    }

    override suspend fun getForeCastWeather(): MutableLiveData<Result<WeatherForeCast>> {
        val liveData = MutableLiveData<Result<WeatherForeCast>>()

        if(response == Response.SUCCESS){
            liveData.value = Result.Success(WeatherForeCast());
        }else{
            liveData.value = Result.Error(Exception("something went wrong"))
        }

        return liveData
    }

}