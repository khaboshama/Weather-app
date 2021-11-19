package com.khaled.weatherapp.feature.search.module.data

import com.khaled.weatherapp.common.data.AppResult
import com.khaled.weatherapp.common.data.IBaseRepository
import com.khaled.weatherapp.feature.search.module.domain.WeatherItem

interface IWeatherRepository : IBaseRepository {
    suspend fun searchByCityName(query: String): AppResult<WeatherItem>
}