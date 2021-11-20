package com.khaled.weatherapp.feature.search.module.domain

import com.google.gson.annotations.SerializedName

data class WeatherItem(
    @SerializedName("coord")
    val coordinate: Coordinate,
    val weather: List<Weather>,
    val main: Main,
    val visibility: Int,
    val wind: Wind,
    @SerializedName("sys")
    val weatherSystem: WeatherSystem,
    val name: String
)
