package com.khaled.weatherapp.feature.search.module.domain

import com.google.gson.annotations.SerializedName

data class Main(
    val temp: Double,
    val pressure: Int,
    val humidity: Int,
    @SerializedName("temp_min")
    val minTemp: Double,
    @SerializedName("temp_max")
    val maxTemp: Double,
)