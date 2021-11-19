package com.khaled.weatherapp.feature.search.module.domain

import com.google.gson.annotations.SerializedName

data class Coordinate(
    val lat: Double,
    @SerializedName("lon")
    val lng: Double
)