package com.anonymous.weatherapp.data.utils

import com.anonymous.weatherapp.data.mapper.Mapper
import com.anonymous.weatherapp.domain.utils.NetworkResult
import com.skydoves.sandwich.ApiResponse

fun <In, Out> callAPI(
    apiResponse: ApiResponse<In>,
    mapper: Mapper<In, Out>,
): NetworkResult<Out> {
    return when (apiResponse) {
        is ApiResponse.Success -> {
            try {
                val result = mapper.mapTo(apiResponse.data)
                NetworkResult.Success(result)
            } catch (ex: NetworkException) {
                NetworkResult.Error(ex.errorCode)
            }
        }
        else -> {
            NetworkResult.Error("401")
        }
    }
}
