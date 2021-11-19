package com.khaled.weatherapp.data.remote.endpoint

import com.khaled.weatherapp.feature.search.module.domain.WeatherItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("data/2.5/weather")
    suspend fun getWeatherByCityName(
        @Query("q") query: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric",
    ): Response<WeatherItem>
}