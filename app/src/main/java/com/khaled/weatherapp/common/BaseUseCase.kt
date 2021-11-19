package com.khaled.weatherapp.common

import com.khaled.weatherapp.common.data.IBaseRepository

abstract class BaseUseCase<Repository : IBaseRepository>(val repository: Repository)