package com.anonymous.weatherapp.data.fake_data

import ForeCast
import ForeCastNetwork
import Main
import Weather
import com.skydoves.sandwich.ApiResponse
import retrofit2.Response

internal fun getFakeForeCastNetworkData(): ForeCastNetwork =
    ForeCastNetwork(
        list = listOf(
            ForeCast(
                dtTxt = "2022-10-09 06:00:00",
                weather = listOf(Weather(icon = "1d")),
                main = Main(temp = 10.0)
            )
        )
    )

fun getFakeErrorForeCastNetworkData(): ForeCastNetwork = ForeCastNetwork(cod = "401")

internal const val fakeErrorCode_401 = "401"
internal const val fakeErrorCode_500 = "500"

internal fun getFakeForeCastNetwork(): ApiResponse<ForeCastNetwork> {
    val response = Response.success(ForeCastNetwork())
    return ApiResponse.Success(response)
}

internal fun getFakeForeCastNetworkWithException(errorCode: String): ApiResponse<ForeCastNetwork> {
    val response = Response.success(ForeCastNetwork(cod = errorCode))
    return ApiResponse.Success(response)
}

internal fun getFakeForeCastList(): ApiResponse<ForeCastNetwork> {
    val response = Response.success(ForeCastNetwork())
    return ApiResponse.Success(response)
}

internal fun getFakeErrorForeCastList(): ApiResponse<ForeCastNetwork> {
    val response = Response.success(ForeCastNetwork(cod = fakeErrorCode_401))
    return ApiResponse.Success(response)
}