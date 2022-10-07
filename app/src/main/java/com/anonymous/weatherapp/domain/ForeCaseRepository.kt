package com.anonymous.weatherapp.domain

import kotlinx.coroutines.flow.Flow

interface ForeCaseRepository {
    fun getForecast(): Flow<NetworkResult<List<ForecastDomain>>>
}
