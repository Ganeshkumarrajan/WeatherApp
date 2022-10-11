package com.anonymous.weatherapp.domain

import com.anonymous.weatherapp.domain.model.ForecastDomain
import com.anonymous.weatherapp.domain.repository.ForeCaseRepository
import com.anonymous.weatherapp.domain.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface GetForecastUseCase {
    suspend fun execute(validCityName: String): Flow<NetworkResult<List<ForecastDomain>>>
}

class GetForeCastUseCaseImpl(private val repository: ForeCaseRepository) : GetForecastUseCase {
    override suspend fun execute(validCityName: String): Flow<NetworkResult<List<ForecastDomain>>> =
        repository.getForecast(
            validCityName
        )
}
