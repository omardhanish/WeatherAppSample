package com.example.weatherappsample.Util

import kotlin.math.roundToInt

object CommonUtils {

    fun convertKelvinToCelsius(value: Double?) : String
    {
        if(value == null)
            return "null"
        else
            return (value - 273.15).roundToInt().toString() + "\u2103"
    }

}