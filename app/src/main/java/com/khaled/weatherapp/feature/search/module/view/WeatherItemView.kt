package com.khaled.weatherapp.feature.search.module.view

import com.khaled.weatherapp.feature.search.module.domain.*

data class WeatherItemView(
    val coordinate: Coordinate,
    val weather: Weather,
    val main: Main,
    val visibility: Int,
    val wind: Wind,
    val weatherSystem: WeatherSystem,
    val name: String
)
