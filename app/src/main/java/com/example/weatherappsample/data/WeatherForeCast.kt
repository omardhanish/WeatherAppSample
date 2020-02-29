package com.example.weatherappsample.data
import com.google.gson.annotations.SerializedName


data class WeatherForeCast(
    @SerializedName("city")
    val city: City? = null,
    @SerializedName("cnt")
    val cnt: Int =  -1,
    @SerializedName("cod")
    val cod: String = "",
    @SerializedName("list")
    val list: List<X> = emptyList(),
    @SerializedName("message")
    val message: Int = -1
)

data class City(
    @SerializedName("coord")
    val coord: Coord,
    @SerializedName("country")
    val country: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("population")
    val population: Int,
    @SerializedName("sunrise")
    val sunrise: Int,
    @SerializedName("sunset")
    val sunset: Int,
    @SerializedName("timezone")
    val timezone: Int
)


data class X(
    @SerializedName("clouds")
    val clouds: Clouds,
    @SerializedName("dt")
    val dt: Int,
    @SerializedName("dt_txt")
    val dtTxt: String,
    @SerializedName("main")
    val main: Main,
    @SerializedName("sys")
    val sys: Sys,
    @SerializedName("weather")
    val weather: List<Weather>,
    @SerializedName("wind")
    val wind: Wind
)
