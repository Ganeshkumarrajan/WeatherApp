package com.anonymous.weatherapp.data.fake_data

import com.anonymous.weatherapp.data.model.ForeCast
import com.anonymous.weatherapp.data.model.ForeCastNetworkData
import com.anonymous.weatherapp.data.model.Main
import com.anonymous.weatherapp.data.model.Weather
import com.skydoves.sandwich.ApiResponse
import retrofit2.Response

internal fun getFakeForeCastNetworkData(): ForeCastNetworkData =
    ForeCastNetworkData( cod = "200",
        list = listOf(
            ForeCast(
                dtTxt = "2022-10-09 06:00:00",
                weather = listOf(Weather(icon = "1d")),
                main = Main(temp = 10.0)
            )
        )
    )

fun getFakeErrorForeCastNetworkData(): ForeCastNetworkData = ForeCastNetworkData(cod = "401")

internal const val fakeErrorCode_401 = "401"
internal const val fakeErrorCode_500 = "500"

internal fun getFakeForeCastNetwork(): ApiResponse<ForeCastNetworkData> {
    val response = Response.success(ForeCastNetworkData())
    return ApiResponse.Success(response)
}

internal fun getFakeForeCastNetworkWithException(errorCode: String): ApiResponse<ForeCastNetworkData> {
    val response = Response.success(ForeCastNetworkData(cod = errorCode))
    return ApiResponse.Success(response)
}

internal fun getFakeForeCastList(): ApiResponse<ForeCastNetworkData> {
    val response = Response.success(ForeCastNetworkData(cod = "200"))
    return ApiResponse.Success(response)
}

internal fun getFakeErrorForeCastList(): ApiResponse<ForeCastNetworkData> {
    val response = Response.success(ForeCastNetworkData(cod = fakeErrorCode_401))
    return ApiResponse.Success(response)
}