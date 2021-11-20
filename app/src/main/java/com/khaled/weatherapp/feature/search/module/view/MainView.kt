package com.khaled.weatherapp.feature.search.module.view

import com.google.gson.annotations.SerializedName

data class MainView(
    val temp: String,
    val pressure: String,
    val humidity: String,
    @SerializedName("temp_min")
    val minTemp: String,
    @SerializedName("temp_max")
    val maxTemp: String,
)