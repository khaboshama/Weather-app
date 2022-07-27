package com.khaled.weatherapp.feature.citySearch.screen

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.khaled.weatherapp.TestCoroutineRule
import com.khaled.weatherapp.feature.search.module.data.IWeatherRepository
import com.khaled.weatherapp.feature.search.module.usecase.GetCityWeatherByLocationUseCase
import com.khaled.weatherapp.feature.search.module.usecase.SearchCityByNameUseCase
import com.khaled.weatherapp.feature.search.screen.CitySearchViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
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
            Assert.assertEquals(5,5)
        }
    }

    @Test
    fun getCityByName_getCityName() {
        testCoroutineRule.runBlockingTest {
            viewModel = CitySearchViewModel(searchCityByNameUseCase, getCityWeatherByLocationUseCase)
            val cityName = "Alexandria"
            testCoroutineRule.pauseDispatcher()
            viewModel.onCitySearchByNameChanged(cityName)
            Assert.assertEquals(viewModel.showLoading.value,true)
            testCoroutineRule.resumeDispatcher()
            Assert.assertEquals(viewModel.showLoading.value,false)
            Assert.assertEquals(5,5)
        }
    }
}