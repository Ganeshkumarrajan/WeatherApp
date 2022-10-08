package com.anonymous.weatherapp.domain

import kotlinx.coroutines.flow.Flow

interface ForeCaseRepository {
    fun getForecast(validCityName: String): Flow<NetworkResult<List<ForecastDomain>>>
}
