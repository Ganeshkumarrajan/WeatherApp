package com.anonymous.weatherapp.presentation.model

sealed class UIState<T> {
    class Nothing<T>: UIState<T>()
    class Loading<T> : UIState<T>()
    data class Success<T>(val data: T) : UIState<T>()
     class Error<T> : UIState<T>()
}
