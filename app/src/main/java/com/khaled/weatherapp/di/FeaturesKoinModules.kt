package com.khaled.weatherapp.di

import com.khaled.weatherapp.MainViewModel
import com.khaled.weatherapp.common.ApplicationContext
import com.khaled.weatherapp.common.IApplicationContext
import com.khaled.weatherapp.feature.search.module.data.IWeatherRepository
import com.khaled.weatherapp.feature.search.module.data.WeatherRepository
import com.khaled.weatherapp.feature.search.module.usecase.GetCityWeatherByLocationUseCase
import com.khaled.weatherapp.feature.search.module.usecase.SearchCityByNameUseCase
import com.khaled.weatherapp.feature.search.screen.CitySearchViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object FeaturesKoinModules {

    val allModules = ArrayList<Module>().apply {
        // application helpers module
        add(getAppHelperModule())
        // main screen
        add(getMainModule())
        // City search screen
        add(getCitySearchModule())
    }

    private fun getCitySearchModule() = module {
        factory<IWeatherRepository> { WeatherRepository() }
        factory { SearchCityByNameUseCase(get()) }
        factory { GetCityWeatherByLocationUseCase(get()) }
        viewModel { CitySearchViewModel(get(),get()) }
    }

    private fun getMainModule() = module { viewModel { MainViewModel() } }

    private fun getAppHelperModule() = module {
        single<IApplicationContext> { ApplicationContext(androidContext()) }
    }
}