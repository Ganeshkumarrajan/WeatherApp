package com.anonymous.weatherapp.data.mapper

import com.anonymous.weatherapp.data.fake_data.getFakeErrorForeCastNetworkData
import com.anonymous.weatherapp.data.fake_data.getFakeForeCastNetworkData
import com.anonymous.weatherapp.data.utils.NetworkException
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName

class ForeCastNetworkToDomainMapperTest {

    @DisplayName("convert valid network data to forecast domain data")
    @Test
    fun testSuccessCase() {
        val result = ForeCastNetworkToDomainMapper().mapTo(getFakeForeCastNetworkData())
        assertEquals(1, result.size)
        assertEquals("10", result[0].temp)
        assertEquals("1d", result[0].temperatureImagePath)
        assertEquals("Oct 09", result[0].date)
    }

    @DisplayName("throws exception for error code on network data")
    @Test
    fun failureCase() {
        org.junit.jupiter.api.assertThrows<NetworkException> {
            ForeCastNetworkToDomainMapper().mapTo(getFakeErrorForeCastNetworkData())
        }
    }
}
