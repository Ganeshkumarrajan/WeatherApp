package com.anonymous.weatherapp.domain.useCase

import com.anonymous.weatherapp.domain.model.ForecastDomain
import com.anonymous.weatherapp.domain.repository.ForeCaseRepository
import com.anonymous.weatherapp.domain.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetForecastUseCase {
    suspend fun execute(validCityName: String): Flow<NetworkResult<List<ForecastDomain>>>
}

class GetForeCastUseCaseImpl @Inject constructor(private val repository: ForeCaseRepository) :
    GetForecastUseCase {
    override suspend fun execute(validCityName: String): Flow<NetworkResult<List<ForecastDomain>>> =
        repository.getForecast(
            validCityName
        )
}
