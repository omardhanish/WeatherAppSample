package com.example.weatherappsample.weatherDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherappsample.data.source.WeatherRepository

class WeatherDetailViewModel(
    private val weatherRepository: WeatherRepository) : ViewModel()
{

}

@Suppress("UNCHECKED_CAST")
class WeatherViewModelFactory (
    private val weatherRepository: WeatherRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        (WeatherDetailViewModel(weatherRepository) as T)
}