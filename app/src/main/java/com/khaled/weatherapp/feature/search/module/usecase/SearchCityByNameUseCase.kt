package com.khaled.weatherapp.feature.search.module.usecase

import com.khaled.weatherapp.common.BaseUseCase
import com.khaled.weatherapp.common.data.AppResult
import com.khaled.weatherapp.feature.search.module.data.IWeatherRepository
import com.khaled.weatherapp.feature.search.module.domain.WeatherItem

class SearchCityByNameUseCase(repository: IWeatherRepository) : BaseUseCase<IWeatherRepository>(repository) {
    suspend operator fun invoke(query: String): AppResult<WeatherItem> {
        return repository.searchByCityName(query)
    }
}