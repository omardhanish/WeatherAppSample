package com.example.weatherappsample.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.weatherappsample.data.source.DefaultWeatherRepository
import com.example.weatherappsample.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.hamcrest.core.IsEqual
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class DefaultWeatherRepositoryTest {

    private lateinit var defaultWeatherRepository: DefaultWeatherRepository

    private lateinit var weatherDataSource: FakeRemoteWeatherDataSource

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        weatherDataSource = FakeRemoteWeatherDataSource()

        defaultWeatherRepository = DefaultWeatherRepository(weatherDataSource)
    }


    @Test
    fun getCurrentWeather_apiResponseSuccess() = runBlockingTest {
        weatherDataSource.response = FakeRemoteWeatherDataSource.Response.SUCCESS
        val  liveData = defaultWeatherRepository.getCurrentWeather()

        val value = liveData.getOrAwaitValue()
        val isSuccess : Boolean = (value is Result.Success)

        MatcherAssert.assertThat(
            isSuccess,
            CoreMatchers.`is`(true)
        )
    }


    @Test
    fun getCurrentWeather_apiResponseFailure() = runBlockingTest {
        weatherDataSource.response = FakeRemoteWeatherDataSource.Response.FAILURE
        val  liveData = defaultWeatherRepository.getCurrentWeather()

        val value = liveData.getOrAwaitValue()
        val isFailed : Boolean = (value is Result.Error)

        MatcherAssert.assertThat(
            isFailed,
            CoreMatchers.`is`(true)
        )
    }

    @Test
    fun getForeCastWeather_apiResponseSuccess() = runBlockingTest {
        weatherDataSource.response = FakeRemoteWeatherDataSource.Response.SUCCESS
        val  liveData = defaultWeatherRepository.getForeCastWeather()

        val value = liveData.getOrAwaitValue()
        val isSuccess : Boolean = (value is Result.Success)

        MatcherAssert.assertThat(
            isSuccess,
            CoreMatchers.`is`(true)
        )
    }

    @Test
    fun getForeCastWeather_apiResponseFailure() = runBlockingTest {
        weatherDataSource.response = FakeRemoteWeatherDataSource.Response.FAILURE
        val  liveData = defaultWeatherRepository.getForeCastWeather()

        val value = liveData.getOrAwaitValue()
        val isFailed : Boolean = (value is Result.Error)

        MatcherAssert.assertThat(
            isFailed,
            CoreMatchers.`is`(true)
        )
    }

}