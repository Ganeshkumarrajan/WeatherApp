package com.anonymous.weatherapp.data.mapper

interface Mapper<in In, out Out> {
    fun mapTo(input: In): Out
}
