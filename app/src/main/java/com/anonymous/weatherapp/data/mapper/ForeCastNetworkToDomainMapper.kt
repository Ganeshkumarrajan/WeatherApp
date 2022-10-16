package com.anonymous.weatherapp.data.mapper

import com.anonymous.weatherapp.data.model.ForeCastNetworkData
import com.anonymous.weatherapp.data.utils.NetworkException
import com.anonymous.weatherapp.domain.model.ForecastDomain
import java.text.SimpleDateFormat
import java.util.*


class ForeCastNetworkToDomainMapper :
    Mapper<ForeCastNetworkData, List<ForecastDomain>> {
    @Throws(Exception::class)
    override fun mapTo(input: ForeCastNetworkData): List<ForecastDomain> {
        if (input.cod?.toInt() != 200) throw NetworkException(input.cod ?: "")
        val result = input.list?.map {
            ForecastDomain(
                date = convertDay(it.dtTxt?.split(" ")?.get(0) ?: ""),
                temperatureImagePath = "https://openweathermap.org/img/wn/${it.weather?.get(0)?.icon ?: ""}@2x.png",
                temp = (it.main?.temp?.toInt().toString())
            )
        }?.distinctBy { it.date }
        return result ?: emptyList()
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
