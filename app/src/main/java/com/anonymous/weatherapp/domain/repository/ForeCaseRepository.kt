package com.anonymous.weatherapp.domain.repository

import com.anonymous.weatherapp.domain.utils.NetworkResult
import com.anonymous.weatherapp.domain.model.ForecastDomain
import kotlinx.coroutines.flow.Flow

interface ForeCaseRepository {
    fun getForecast(validCityName: String): Flow<NetworkResult<List<ForecastDomain>>>
}
