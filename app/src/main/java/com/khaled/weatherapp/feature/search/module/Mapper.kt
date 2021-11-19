package com.khaled.weatherapp.feature.search.module

import com.khaled.weatherapp.feature.search.module.domain.WeatherItem
import com.khaled.weatherapp.feature.search.module.view.WeatherItemView

object Mapper {

    fun WeatherItem.toWeatherItemView() = WeatherItemView(
        coordinate = coordinate,
        weather = weather,
        main = main,
        visibility = visibility,
        wind = wind,
        weatherSystem = weatherSystem,
        name = name
    )
}