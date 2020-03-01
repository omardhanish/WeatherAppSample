package com.example.weatherappsample.data.source.remote

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.weatherappsample.data.CurrentWeather
import com.example.weatherappsample.data.Result
import com.example.weatherappsample.data.WeatherForeCast
import com.example.weatherappsample.data.source.WeatherDataSource
import com.google.gson.Gson
import kotlinx.coroutines.*
import retrofit2.Response
import java.lang.Exception

class WeatherRemoteDataSource(
    private val weatherApi : WeatherApi,
    private val disPatcher: CoroutineDispatcher = Dispatchers.IO
) : WeatherDataSource
{

    override suspend fun getCurrentWeather(): Result<CurrentWeather> {

        try
        {
            val response = withContext(disPatcher) {
                weatherApi.getCurrentTemp()
            }

            if(response.isSuccessful){
                return Result.Success(response.body()!!)
            }else{
                return Result.Error(Exception("Something went Wrong"))
            }
        }catch (e: Exception) {
            return Result.Error(e)
        }
    }


    override suspend fun getForeCastWeather(): Result<WeatherForeCast> {
        try
        {
            val response = withContext(disPatcher){
                weatherApi.getForecast()
            }

            if(response.isSuccessful){
                return Result.Success(response.body()!!)
            }else{
                return Result.Error(Exception())
            }
        }catch (e: Exception) {
            return Result.Error(e)
        }

    }

}