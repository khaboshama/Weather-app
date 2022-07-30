package com.khaled.weatherapp.feature.citySearch.screen

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.khaled.weatherapp.TestCoroutineRule
import com.khaled.weatherapp.common.data.AppResult
import com.khaled.weatherapp.feature.search.module.Mapper.toWeatherItemView
import com.khaled.weatherapp.feature.search.module.data.IWeatherRepository
import com.khaled.weatherapp.feature.search.module.domain.*
import com.khaled.weatherapp.feature.search.module.usecase.GetCityWeatherByLocationUseCase
import com.khaled.weatherapp.feature.search.module.usecase.SearchCityByNameUseCase
import com.khaled.weatherapp.feature.search.screen.CitySearchViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class CitySearchViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @get:Rule
    var testCoroutineRule = TestCoroutineRule()

    @Mock
    lateinit var repository: IWeatherRepository

    private val getCityWeatherByLocationUseCase by lazy { GetCityWeatherByLocationUseCase(repository) }
    private val searchCityByNameUseCase by lazy { SearchCityByNameUseCase(repository) }
    lateinit var viewModel: CitySearchViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun testFirst() {
        testCoroutineRule.runBlockingTest {
            viewModel = CitySearchViewModel(searchCityByNameUseCase, getCityWeatherByLocationUseCase)
            Assert.assertEquals(5, 5)
        }
    }

    @Test
    fun getCityByName_getCityName() {
        testCoroutineRule.runBlockingTest {
            viewModel = CitySearchViewModel(searchCityByNameUseCase, getCityWeatherByLocationUseCase)
            val cityName = "Alexandria"
            val coordinate = Coordinate(1.0, 2.0)
            Assert.assertEquals(coordinate, coordinate)
            val wind = Wind(2.2)
            Assert.assertEquals(wind, wind)
            val weatherSystem = WeatherSystem("egypt")
            Assert.assertEquals(weatherSystem, weatherSystem)
            val main = Main(
                temp = 1.2,
                pressure = 1,
                humidity = 2,
                minTemp = 2.2,
                maxTemp = 4.6
            )
            val item = WeatherItem(
                coordinate = coordinate,
                listOf(Weather("main", "description", "icon")),
                main = main,
                visibility = 20,
                wind = wind,
                weatherSystem = weatherSystem,
                name = "any name"
            )
            Mockito.`when`(repository.searchByCityName(cityName)).thenReturn(AppResult.Success(item))
            testCoroutineRule.pauseDispatcher()
            viewModel.onCitySearchByNameChanged(cityName)
            Assert.assertEquals(viewModel.showLoading.value, true)
            testCoroutineRule.resumeDispatcher()
            Assert.assertEquals(viewModel.showLoading.value, false)
            Assert.assertEquals(viewModel.showErrorView.value, null)
            Assert.assertEquals(viewModel.cityWeatherItemView.value, item.toWeatherItemView())
        }
    }

    @Test
    fun getCityByName_getError() {
        testCoroutineRule.runBlockingTest {
            viewModel = CitySearchViewModel(searchCityByNameUseCase, getCityWeatherByLocationUseCase)
            val cityName = "Alexandria"
            val errorMessage = "something went wrong"
            Mockito.`when`(repository.searchByCityName(cityName)).thenReturn(AppResult.Error(errorMessage = errorMessage))
            testCoroutineRule.pauseDispatcher()
            viewModel.onCitySearchByNameChanged(cityName)
            Assert.assertEquals(viewModel.showLoading.value, true)
            testCoroutineRule.resumeDispatcher()
            Assert.assertEquals(viewModel.showLoading.value, false)
            Assert.assertEquals(viewModel.showErrorView.value, errorMessage)
        }
    }
}