package com.anonymous.weatherapp.domain

sealed class NetworkResult<in T> {
    data class Success<T>(val data: T) : NetworkResult<T>()
    data class Error<T>(val errorCode: String) : NetworkResult<T>()
}
