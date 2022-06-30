package com.khaled.weatherapp.feature.citySearch.useCase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.khaled.weatherapp.TestCoroutineRule
import com.khaled.weatherapp.common.data.AppResult
import com.khaled.weatherapp.feature.search.module.data.IWeatherRepository
import com.khaled.weatherapp.feature.search.module.domain.*
import com.khaled.weatherapp.feature.search.module.usecase.GetCityWeatherByLocationUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.anyDouble
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class GetCityWeatherByLocationUseCaseTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()
    var testCoroutineRule = TestCoroutineRule()

    @Mock
    lateinit var repository: IWeatherRepository

    private val getCityWeatherByLocationUseCase by lazy { GetCityWeatherByLocationUseCase(repository) }

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun getCityWeatherByLocation_completed() {
        testCoroutineRule.runBlockingTest {

            val coordinate = Coordinate(1.0, 2.0)
            assertEquals(coordinate, coordinate)
            val wind = Wind(2.2)
            assertEquals(wind, wind)
            val weatherSystem = WeatherSystem("egypt")
            assertEquals(weatherSystem, weatherSystem)
            val main = Main(
                temp = 1.2,
                pressure = 1,
                humidity = 2,
                minTemp = 2.2,
                maxTemp = 4.6
            )
            val item = WeatherItem(
                coordinate = coordinate, emptyList(), main = main, visibility = 20, wind = wind,
                weatherSystem = weatherSystem, name = "any name"
            )
            assertEquals(item, item)
            Mockito.`when`(
                repository.getCityWeatherByLocation(anyDouble(), anyDouble())
            ).thenReturn(AppResult.Success(item))
            val result = getCityWeatherByLocationUseCase.invoke(anyDouble(), anyDouble())
            assertEquals((result as AppResult.Success).data.name, "any name")
        }
    }
}