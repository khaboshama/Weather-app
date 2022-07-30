package com.khaled.weatherapp.feature.citySearch.screen

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.khaled.weatherapp.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.mockito.MockitoAnnotations

class TestViewModel {

    @Rule
    @JvmField
    val instance = InstantTaskExecutorRule()

    @Rule
    @ExperimentalCoroutinesApi
    val testCoroutineDispatcher = TestCoroutineRule()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }


}