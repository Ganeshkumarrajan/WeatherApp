package com.anonymous.weatherapp.data.repository

import ForeCastNetwork
import com.anonymous.weatherapp.data.api.WeatherService
import com.anonymous.weatherapp.data.mapper.Mapper
import com.anonymous.weatherapp.data.utils.callAPI
import com.anonymous.weatherapp.domain.repository.ForeCaseRepository
import com.anonymous.weatherapp.domain.model.ForecastDomain
import com.anonymous.weatherapp.domain.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ForeCastRepositoryImpl(
    private val weatherServer: WeatherService,
    private val mapper: Mapper<ForeCastNetwork, List<ForecastDomain>>
) : ForeCaseRepository {

    override fun getForecast(validCityName: String): Flow<NetworkResult<List<ForecastDomain>>> =
        flow {
            emit(callAPI(weatherServer.getForeCast(), mapper))
        }
}
