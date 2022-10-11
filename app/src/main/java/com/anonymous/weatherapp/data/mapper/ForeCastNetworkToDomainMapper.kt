package com.anonymous.weatherapp.data.mapper

import ForeCastNetwork
import com.anonymous.weatherapp.data.utils.NetworkException
import com.anonymous.weatherapp.domain.model.ForecastDomain
import java.text.SimpleDateFormat
import java.util.*

class ForeCastNetworkToDomainMapper : Mapper<ForeCastNetwork, List<ForecastDomain>> {
    @Throws(Exception::class)
    override fun mapTo(input: ForeCastNetwork): List<ForecastDomain> {
        if (input.cod?.isNotEmpty() == true) throw NetworkException(input.cod ?: "")
        return input.list?.map {
            ForecastDomain(
                date = convertDay(it.dtTxt?.split(" ")?.get(0) ?: ""),
                temperatureImagePath = it.weather?.get(0)?.icon ?: "",
                temp = (it.main?.temp?.toInt().toString())
            )
        } ?: emptyList()
    }

    private fun convertDay(day: String): String {
        val sourceDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        val exceptedResultDateFormat = SimpleDateFormat("MMM dd", Locale.ENGLISH)
        return try {
            sourceDateFormat.parse(day)?.let {
                exceptedResultDateFormat.format(it)
            } ?: ""
        } catch (e: Exception) {
            ""
        }
    }
}
