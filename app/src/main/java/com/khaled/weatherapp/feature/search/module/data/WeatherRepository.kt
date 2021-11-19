package com.khaled.weatherapp.feature.search.module.data

import com.khaled.weatherapp.common.data.AppResult
import com.khaled.weatherapp.common.data.HttpUtils
import com.khaled.weatherapp.constant.Constant.API_KEY
import com.khaled.weatherapp.data.remote.RetrofitClient
import com.khaled.weatherapp.feature.search.module.domain.WeatherItem

class WeatherRepository : IWeatherRepository {

    override suspend fun searchByCityName(query: String): AppResult<WeatherItem> {
        val errorAppResult: AppResult.Error?

        return when (val result =
            HttpUtils.safeApiCall { RetrofitClient.weatherApi.getWeatherByCityName(query, apiKey = API_KEY) }) {
            is AppResult.Success -> AppResult.Success(result.data)
            else -> {
                errorAppResult = result as AppResult.Error
                getErrorAppResult(errorAppResult.errorMessage, errorAppResult.errorMessageRes)
            }
        }
    }

    private fun getErrorAppResult(errorMessage: String?, errorMessageRes: Int?) = AppResult.Error(
        errorMessage = errorMessage,
        errorMessageRes = errorMessageRes
    )
}