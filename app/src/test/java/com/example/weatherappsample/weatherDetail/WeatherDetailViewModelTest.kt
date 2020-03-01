package com.example.weatherappsample.weatherDetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.weatherappsample.data.FakeRemoteWeatherDataSource
import com.example.weatherappsample.data.source.DefaultWeatherRepository
import com.example.weatherappsample.getOrAwaitValue
import com.example.weatherappsample.weatherDetails.WeatherDetailViewModel
import com.example.weatherappsample.weatherDetails.ui
import kotlinx.coroutines.*
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.not
import org.hamcrest.MatcherAssert
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class WeatherDetailViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var defaultWeatherRepository: DefaultWeatherRepository

    private lateinit var weatherDataSource: FakeRemoteWeatherDataSource

    private lateinit var weatherDetailViewModel : WeatherDetailViewModel

    @Before
    fun setUp() {
        // for testing Purpose viewModelScope dispatcher is changed from Main to Unconfined
        ui = Dispatchers.Unconfined

        weatherDataSource = FakeRemoteWeatherDataSource()

        defaultWeatherRepository = DefaultWeatherRepository(weatherDataSource)

        weatherDetailViewModel = WeatherDetailViewModel(defaultWeatherRepository)
    }

    @Test
    fun callWeatherApi_checkCurrentWeatherAndForeCastWeatherEventTriggered() = runBlocking{

        // given callWeatherApi is sucess
        weatherDataSource.response = FakeRemoteWeatherDataSource.Response.SUCCESS
        weatherDetailViewModel.callWeatherApi()

        //when the currentWeather and foreCastWeather should be triggered
        val currentWeatherValue = weatherDetailViewModel.currentWeather.getOrAwaitValue()
        val forecastWeatherValue = weatherDetailViewModel.foreCastWeather.getOrAwaitValue()
        // also check dataLoading Event
        val dataLoadingValue = weatherDetailViewModel.dataLoading.getOrAwaitValue()

        //then
        assertThat(currentWeatherValue, not(CoreMatchers.nullValue()))
        assertThat(forecastWeatherValue, not(CoreMatchers.nullValue()))
        assertThat(dataLoadingValue, CoreMatchers.`is`(false))

    }

    @Test
    fun callWeatherApi_checkWhenApiResponseIsErrorThenErrorEventIsTriggered_isTrue()
    {
        //given response is Error then
        weatherDataSource.response = FakeRemoteWeatherDataSource.Response.FAILURE
        weatherDetailViewModel.callWeatherApi()

        //when errorLoading Event Is Triggered
        val errorValue = weatherDetailViewModel.error.getOrAwaitValue()

        //then
        assertThat(errorValue, not(CoreMatchers.nullValue()))
        assertThat(errorValue, CoreMatchers.`is`(true))
    }
}