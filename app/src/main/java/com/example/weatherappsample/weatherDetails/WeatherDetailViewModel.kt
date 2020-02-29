package com.example.weatherappsample.weatherDetails


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.weatherappsample.WeatherApplication
import com.example.weatherappsample.data.source.WeatherRepository
import kotlinx.coroutines.launch

class WeatherDetailViewModel(private val weatherRepository: WeatherRepository) : ViewModel()
{

    fun checkApiLogSample()
    {
        viewModelScope.launch {
            weatherRepository.getCurrentWeather()
            weatherRepository.getForeCastWeather()
        }
    }

}

@Suppress("UNCHECKED_CAST")
class WeatherDetailViewModelFactory (
    private val weatherRepository: WeatherRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        (WeatherDetailViewModel(weatherRepository) as T)
}