package com.khaled.weatherapp.feature.search.screen

import androidx.lifecycle.MutableLiveData
import com.khaled.weatherapp.common.BaseViewModel
import com.khaled.weatherapp.feature.search.module.Mapper.toWeatherItemView
import com.khaled.weatherapp.feature.search.module.usecase.SearchCityByNameUseCase
import com.khaled.weatherapp.feature.search.module.view.WeatherItemView
import kotlinx.coroutines.Job

class CitySearchViewModel(
    private val searchCityByNameUseCase: SearchCityByNameUseCase,
) : BaseViewModel() {
    private var job: Job? = null
    private var currentQuery = ""
    private var isWeatherCityByNameResponseFinished = true
    val cityWeatherItemView = MutableLiveData<WeatherItemView?>()

    fun getCityByName() {
        wrapBlockingOperation {
            handleResult(
                searchCityByNameUseCase.invoke(query = currentQuery),
                onSuccess = {
                    cityWeatherItemView.value = it.data.toWeatherItemView()
                },
                onError = {
                    error.value = getErrorMessage(it)!!
                    isWeatherCityByNameResponseFinished = true
                }
            )
        }
    }

    fun onSearchChanged(query: String) {
        val queryFormatted = query.trim()
        if (queryFormatted.isEmpty()) return
        clearData()
        if (isWeatherCityByNameResponseFinished.not()) return
        isWeatherCityByNameResponseFinished = false
        currentQuery = queryFormatted
        getCityByName()
    }

    private fun clearData() {
        job?.cancel()
        cityWeatherItemView.value = null
        isWeatherCityByNameResponseFinished = true
    }
}