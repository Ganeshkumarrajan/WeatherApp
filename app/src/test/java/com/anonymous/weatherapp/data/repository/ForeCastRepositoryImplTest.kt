package com.anonymous.weatherapp.data.repository

import com.anonymous.weatherapp.data.api.WeatherService
import com.anonymous.weatherapp.data.fake_data.getFakeErrorForeCastList
import com.anonymous.weatherapp.data.fake_data.getFakeForeCastList
import com.anonymous.weatherapp.data.mapper.ForeCastNetworkToDomainMapper
import com.anonymous.weatherapp.domain.utils.NetworkResult
import com.anonymous.weatherapp.domain.fake_data.FakeForecast
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName


class ForeCastRepositoryImplTest {

    private var weatherServer = mockk<WeatherService>()
    private val foreCastNetworkToDomainMapper =
        ForeCastNetworkToDomainMapper()
    private lateinit var repositoryImpl: ForeCastRepositoryImpl

    @Before
    fun setUp() {
        repositoryImpl = ForeCastRepositoryImpl(weatherServer, foreCastNetworkToDomainMapper)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @DisplayName("get forecast data on valid city name")
    @Test
    fun testValidCityName() = runBlockingTest {
        coEvery { weatherServer.getForeCast(FakeForecast.validCityName) } returns getFakeForeCastList()

        val forecastList = repositoryImpl.getForecast(validCityName = FakeForecast.validCityName)

        // result
        val result = forecastList.first()
        Assertions.assertEquals(true, result is NetworkResult.Success)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @DisplayName("get error data on invalid city name")
    @Test
    fun testInValidCityName() = runBlockingTest {
        coEvery { weatherServer.getForeCast(FakeForecast.invalidCityName) } returns getFakeErrorForeCastList()

        val forecastList = repositoryImpl.getForecast(validCityName = FakeForecast.invalidCityName)

        // result
        val result = forecastList.first()
        Assertions.assertEquals(true, result is NetworkResult.Error)

    }
}
