package com.khaled.weatherapp.feature.search.module.view

import com.google.gson.annotations.SerializedName

data class CoordinateView(
    val lat: String,
    @SerializedName("lon")
    val lng: String
)