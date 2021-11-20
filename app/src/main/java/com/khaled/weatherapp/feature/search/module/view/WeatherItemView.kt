package com.khaled.weatherapp.feature.search.module.view

data class WeatherItemView(
    val coordinate: CoordinateView,
    val weather: WeatherView,
    val main: MainView,
    val visibility: String,
    val wind: WindView,
    val weatherSystem: WeatherSystemView,
    val name: String
)
