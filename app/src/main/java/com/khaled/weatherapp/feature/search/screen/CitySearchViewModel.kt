package com.khaled.weatherapp.feature.search.screen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.khaled.weatherapp.common.BaseViewModel
import com.khaled.weatherapp.feature.search.module.Mapper.toWeatherItemView
import com.khaled.weatherapp.feature.search.module.usecase.SearchCityByNameUseCase
import com.khaled.weatherapp.feature.search.module.view.WeatherItemView
import com.khaled.weatherapp.utils.SingleLiveEvent
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class CitySearchViewModel(
    private val searchCityByNameUseCase: SearchCityByNameUseCase,
) : BaseViewModel() {
    private var job: Job? = null
    private var currentQuery = ""
    private var isWeatherCityByNameResponseFinished = true
    val cityWeatherItemView = MutableLiveData<WeatherItemView?>()
    val dateView = MutableLiveData<String>()
    val timeView = MutableLiveData<String>()
    val showErrorView = SingleLiveEvent<String>()

    private fun getCityByName() {
        job = wrapBlockingOperation {
            delay(1000)
            handleResult(
                searchCityByNameUseCase.invoke(query = currentQuery),
                onSuccess = {
                    showErrorView.value = null
                    cityWeatherItemView.value = it.data.toWeatherItemView()
                },
                onError = {
                    showErrorView.value = getErrorMessage(it)!!
                    isWeatherCityByNameResponseFinished = true
                }
            )
        }
    }

    fun onCitySearchByNameChanged(query: String) {
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

    init {
        dateView.value = getDateView()
        viewModelScope.launch {
            getTimeView()
        }
    }

    private fun getDateView(): String {
        val sdf = SimpleDateFormat("EEE MMM dd,yyyy", Locale.ENGLISH)
        return sdf.format(Date())
    }

    private suspend fun getTimeView() {
        val sdf = SimpleDateFormat("hh:mm", Locale.ENGLISH)
        timeView.value = sdf.format(Date())
        delay(1000)
        getTimeView()
    }
}