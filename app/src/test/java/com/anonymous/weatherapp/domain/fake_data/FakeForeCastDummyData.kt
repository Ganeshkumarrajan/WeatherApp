package com.anonymous.weatherapp.domain.fake_data

import com.anonymous.weatherapp.domain.model.ForecastDomain
import com.anonymous.weatherapp.domain.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object FakeForecast {
    const val validCityName = "Germany"
    const val invalidCityName = "InvalidCityName"
    const val errorNotFound = "404"
}

suspend fun getFakeEmptyListForecast(): Flow<NetworkResult<List<ForecastDomain>>> =
    emitSuccessData(emptyList<ForecastDomain>())

suspend fun getFakeForecastList(): Flow<NetworkResult<List<ForecastDomain>>> =
    emitSuccessData(
        listOf(
            ForecastDomain(temp = "6", date = "Mon 12", temperatureImagePath = "1d"),
            ForecastDomain(temp = "16", date = "Tue 32", temperatureImagePath = "2d")
        )
    )

fun getFakeForeCastErrorNotFound(): Flow<NetworkResult<List<ForecastDomain>>> =
    emitErrorData(FakeForecast.errorNotFound)

private fun emitErrorData(errorCode: String): Flow<NetworkResult<List<ForecastDomain>>> = flow {
    emit(NetworkResult.Error(errorCode))
}

private suspend fun <T> emitSuccessData(data: List<T>): Flow<NetworkResult<List<ForecastDomain>>> =
    flow {
        emit(NetworkResult.Success(data))
    }
