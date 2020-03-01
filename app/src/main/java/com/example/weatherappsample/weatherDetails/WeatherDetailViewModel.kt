package com.example.weatherappsample.weatherDetails


import androidx.lifecycle.*
import com.example.weatherappsample.data.CurrentWeather
import com.example.weatherappsample.data.ForeCastDetail
import com.example.weatherappsample.data.Result
import com.example.weatherappsample.data.WeatherForeCast
import com.example.weatherappsample.data.source.WeatherRepository
import kotlinx.coroutines.*

class WeatherDetailViewModel(
    private val weatherRepository: WeatherRepository) : ViewModel()
{

    val dataLoading = MutableLiveData<Boolean>()

    val error = MutableLiveData<Boolean>()

    var currentWeather =  MutableLiveData<CurrentWeather>()

    var foreCastWeather =  MutableLiveData<WeatherForeCast>()

    var forecastWeatherList = ArrayList<ForeCastDetail>()

    fun callWeatherApi()
    {
        dataLoading.value = true
        uiJob {
            val currentWeatherResult = weatherRepository.getCurrentWeather()
            val forecastWeather = weatherRepository.getForeCastWeather()
            setApiResultValue(currentWeatherResult , forecastWeather)
        }
    }

    private fun setApiResultValue(currentWeatherResult : Result<CurrentWeather> , forecastWeather : Result<WeatherForeCast>)
    {
        dataLoading.value = false
        if(currentWeatherResult is Result.Success && forecastWeather is Result.Success){
            error.value = false
            currentWeather.value = currentWeatherResult.data
            foreCastWeather.value = forecastWeather.data
        }else{
            error.value = true
        }
    }

}

// for testing purpose
var ui: CoroutineDispatcher = Dispatchers.Main

fun ViewModel.uiJob(block: suspend CoroutineScope.() -> Unit): Job {
    return viewModelScope.launch(ui) {
        block()
    }
}


@Suppress("UNCHECKED_CAST")
class WeatherDetailViewModelFactory (
    private val weatherRepository: WeatherRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        (WeatherDetailViewModel(weatherRepository) as T)
}