package com.anonymous.weatherapp.data.api

import ForeCastNetwork
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET

interface WeatherService {
    @GET("")
    fun getForeCast(): ApiResponse<ForeCastNetwork>
}
