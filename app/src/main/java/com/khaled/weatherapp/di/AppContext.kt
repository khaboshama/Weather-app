package com.khaled.weatherapp.di

import com.khaled.weatherapp.common.IApplicationContext
import org.koin.java.KoinJavaComponent

object AppContext {
    val applicationContext by KoinJavaComponent.getKoin().inject<IApplicationContext>()
}