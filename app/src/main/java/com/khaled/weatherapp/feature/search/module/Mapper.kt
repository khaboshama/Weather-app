package com.khaled.weatherapp.feature.search.module

import com.khaled.weatherapp.feature.search.module.domain.*
import com.khaled.weatherapp.feature.search.module.view.*
import kotlin.math.roundToInt

object Mapper {

    fun WeatherItem.toWeatherItemView() = WeatherItemView(
        coordinate = coordinate.toCoordinateView(),
        weather = weather[0].toWeatherView(),
        main = main.toMainView(),
        visibility = visibility.div(1000.0).roundToInt().toString(),
        wind = wind.toWindView(),
        weatherSystem = weatherSystem.toWeatherSystemView(),
        name = name
    )
}

private fun Coordinate.toCoordinateView() = CoordinateView(
    lat = lat.toString(),
    lng = lng.toString()
)

private fun Weather.toWeatherView() = WeatherView(
    main = main,
    description = description,
    icon = icon
)

private fun Main.toMainView() = MainView(
    temp = temp.toInt().toString(),
    pressure = pressure.toString(),
    humidity = humidity.toString(),
    minTemp = minTemp.roundToInt().toString(),
    maxTemp = maxTemp.roundToInt().toString()
)

private fun Wind.toWindView() = WindView(
    speed = speed.toString()
)

private fun WeatherSystem.toWeatherSystemView() = WeatherSystemView(
    country = country
)

