package com.khaled.weatherapp.feature.search.module.usecase

import com.khaled.weatherapp.common.BaseUseCase
import com.khaled.weatherapp.common.data.AppResult
import com.khaled.weatherapp.feature.search.module.data.IWeatherRepository
import com.khaled.weatherapp.feature.search.module.domain.WeatherItem

class GetCityWeatherByLocationUseCase(repository: IWeatherRepository) : BaseUseCase<IWeatherRepository>(repository) {
    suspend operator fun invoke(lat: Double, lng: Double): AppResult<WeatherItem> {
        return repository.getCityWeatherByLocation(lat, lng)
    }
}