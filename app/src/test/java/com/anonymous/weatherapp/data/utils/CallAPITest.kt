package com.anonymous.weatherapp.data.utils

import com.anonymous.weatherapp.data.model.ForeCastNetworkData
import com.anonymous.weatherapp.data.fake_data.fakeErrorCode_401
import com.anonymous.weatherapp.data.fake_data.fakeErrorCode_500
import com.anonymous.weatherapp.data.fake_data.getFakeForeCastNetwork
import com.anonymous.weatherapp.data.fake_data.getFakeForeCastNetworkWithException
import com.anonymous.weatherapp.data.mapper.ForeCastNetworkToDomainMapper
import com.anonymous.weatherapp.domain.model.ForecastDomain
import com.anonymous.weatherapp.domain.utils.NetworkResult
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName


class APIParserTest() {

    private val mapper = mockk<ForeCastNetworkToDomainMapper>()

    @DisplayName("test success Case")
    @Test
    fun testSuccessCase() {
        coEvery {
            mapper.mapTo(ForeCastNetworkData())
        } returns listOf(ForecastDomain("", "", ""))

        val convertedResult = callAPI(
            getFakeForeCastNetwork(), mapper
        )

        // result
        assertEquals(true, convertedResult is NetworkResult.Success)
        if (convertedResult is NetworkResult.Success)
            assertEquals(true, convertedResult.data.size == 1)
    }

    @DisplayName("test exception Case")
    @Test
    fun testNotFoundExceptionCase() {
        every {
            mapper.mapTo(ForeCastNetworkData(cod = fakeErrorCode_401))
        } throws NetworkException(fakeErrorCode_401)


        val convertedResult = callAPI(
            getFakeForeCastNetworkWithException(fakeErrorCode_401), mapper
        )
        assertEquals(true, convertedResult is NetworkResult.Error)

        if (convertedResult is NetworkResult.Error)
            assertEquals(true, convertedResult.errorCode == fakeErrorCode_401)


    }

    @DisplayName("test exception Case")
    @Test
    fun testInternalErrorExceptionCase() {
        every {
            mapper.mapTo(ForeCastNetworkData(cod = fakeErrorCode_500))
        } throws NetworkException(fakeErrorCode_500)


        val convertedResult = callAPI(
            getFakeForeCastNetworkWithException(fakeErrorCode_500), mapper
        )
        assertEquals(true, convertedResult is NetworkResult.Error)

        if (convertedResult is NetworkResult.Error)
            assertEquals(true, convertedResult.errorCode == fakeErrorCode_500)
    }

    @DisplayName("test network error")
    @Test
    fun testNetworkErrorCase() {
        every {
            mapper.mapTo(ForeCastNetworkData(cod = fakeErrorCode_500))
        } throws NetworkException(fakeErrorCode_500)


        val convertedResult = callAPI(
            getFakeForeCastNetworkWithException(fakeErrorCode_500), mapper
        )
        assertEquals(true, convertedResult is NetworkResult.Error)

        if (convertedResult is NetworkResult.Error)
            assertEquals(true, convertedResult.errorCode == fakeErrorCode_500)
    }


}
