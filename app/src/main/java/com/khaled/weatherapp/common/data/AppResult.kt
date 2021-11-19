package com.khaled.weatherapp.common.data

sealed class AppResult<out R> {
    data class Success<T>(val data: T) : AppResult<T>()
    data class Error(val errorMessage: String? = null, val errorMessageRes: Int? = null) : AppResult<Nothing>()
}