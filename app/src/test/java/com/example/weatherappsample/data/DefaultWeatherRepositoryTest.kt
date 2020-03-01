package com.example.weatherappsample.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.weatherappsample.data.source.DefaultWeatherRepository
import com.example.weatherappsample.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class DefaultWeatherRepositoryTest {

    private lateinit var defaultWeatherRepository: DefaultWeatherRepository

    private lateinit var weatherDataSource: FakeRemoteWeatherDataSource

    @Before
    fun setUp() {
        weatherDataSource = FakeRemoteWeatherDataSource()

        defaultWeatherRepository = DefaultWeatherRepository(weatherDataSource)
    }


    @Test
    fun getCurrentWeather_apiResponseSuccess() = runBlockingTest {

        //given response success
        weatherDataSource.response = FakeRemoteWeatherDataSource.Response.SUCCESS

        //when
        val value = defaultWeatherRepository.getCurrentWeather()
        val isSuccess : Boolean = (value is Result.Success)

        //then
        assertThat(isSuccess, CoreMatchers.`is`(true))
    }


    @Test
    fun getCurrentWeather_apiResponseFailure() = runBlockingTest {
        //given response failure
        weatherDataSource.response = FakeRemoteWeatherDataSource.Response.FAILURE

        //when
        val  value = defaultWeatherRepository.getCurrentWeather()
        val isFailed : Boolean = (value is Result.Error)

        //then
        assertThat(isFailed, CoreMatchers.`is`(true))
    }

    @Test
    fun getForeCastWeather_apiResponseSuccess() = runBlockingTest {

        // given response success
        weatherDataSource.response = FakeRemoteWeatherDataSource.Response.SUCCESS

        //when
        val  value = defaultWeatherRepository.getForeCastWeather()
        val isSuccess : Boolean = (value is Result.Success)

        //then
        assertThat(isSuccess, CoreMatchers.`is`(true))
    }

    @Test
    fun getForeCastWeather_apiResponseFailure() = runBlockingTest {
        //given response failed
        weatherDataSource.response = FakeRemoteWeatherDataSource.Response.FAILURE

        //when
        val value = defaultWeatherRepository.getForeCastWeather()
        val isFailed : Boolean = (value is Result.Error)

        //then
        assertThat(isFailed, CoreMatchers.`is`(true))
    }

}