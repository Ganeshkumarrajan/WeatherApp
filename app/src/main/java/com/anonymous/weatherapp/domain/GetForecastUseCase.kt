package com.anonymous.weatherapp.domain

import kotlinx.coroutines.flow.Flow

interface GetForecastUseCase {
    suspend fun execute(): Flow<NetworkResult<List<ForecastDomain>>>
}

class GetForeCastUseCaseImpl(private val repository: ForeCaseRepository) : GetForecastUseCase{
    override suspend fun execute(): Flow<NetworkResult<List<ForecastDomain>>> =repository.getForecast()
}