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

    override suspend fun getCurrentWeather(): MutableLiveData<Result<CurrentWeather>> {

        val liveData = MutableLiveData<Result<CurrentWeather>>()
        liveData.postValue(Result.Loading)

        val response = withContext(disPatcher) {
            weatherApi.getCurrentTemp()
        }

        try
        {
            if(response.isSuccessful){
                Log.e("CurrentWeather" , Gson().toJson(response.body()))
                liveData.postValue( Result.Success(response.body()!!))
            }else{
                liveData.postValue(Result.Error(Exception("Something went Wrong")))
            }
        }catch (e: Exception) {
            liveData.postValue(Result.Error(e))
        }

        return liveData
    }


    override suspend fun getForeCastWeather(): MutableLiveData<Result<WeatherForeCast>> {

        val liveData = MutableLiveData<Result<WeatherForeCast>>()
        liveData.postValue(Result.Loading)

        val response = withContext(disPatcher){
            weatherApi.getForecast()
        }

        try
        {
            if(response.isSuccessful){
                Log.e("ForeCastWeather" , Gson().toJson(response.body()))
                liveData.postValue(Result.Success(response.body()!!))
            }else{
                liveData.postValue(Result.Error(Exception()))
            }
        }catch (e: Exception) {
            liveData.postValue(Result.Error(e))
        }

        return liveData
    }

}