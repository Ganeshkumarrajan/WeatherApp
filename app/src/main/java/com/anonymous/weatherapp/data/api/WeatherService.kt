package com.anonymous.weatherapp.data.api

import com.anonymous.weatherapp.data.model.ForeCastNetworkData
import com.anonymous.weatherapp.BuildConfig
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("data/2.5/forecast?appid=${BuildConfig.API_KEY}")
    suspend fun getForeCast(@Query("q") cityName:String): ApiResponse<ForeCastNetworkData>
}
