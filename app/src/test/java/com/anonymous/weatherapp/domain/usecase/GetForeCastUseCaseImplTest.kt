package com.anonymous.weatherapp.domain.usecase

import com.anonymous.weatherapp.domain.useCase.GetForeCastUseCaseImpl
import com.anonymous.weatherapp.domain.fake_data.*
import com.anonymous.weatherapp.domain.repository.ForeCaseRepository
import com.anonymous.weatherapp.domain.utils.NetworkResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import org.junit.Assert

@OptIn(ExperimentalCoroutinesApi::class)
class GetForeCastUseCaseImplTest {

    private var repository: ForeCaseRepository = mockk()
    lateinit var useCase: GetForeCastUseCaseImpl

    @Before
    fun setup() {
        useCase = GetForeCastUseCaseImpl(repository)
    }

    @Test
    fun `forecast empty list on valid city name`() = runBlockingTest {
        coEvery {
            repository.getForecast(FakeForecast.validCityName)
        } returns getFakeEmptyListForecast()

        val result = useCase.execute(FakeForecast.validCityName)

        val flowResult = result.first()
        Assert.assertTrue(flowResult is NetworkResult.Success)

        if (flowResult is NetworkResult.Success)
            Assert.assertTrue(flowResult.data.isEmpty())
    }

    @Test
    fun `forecast lists on valid city name`() = runBlockingTest {
        coEvery { repository.getForecast(FakeForecast.validCityName) } returns getFakeForecastList()
        val result = useCase.execute(FakeForecast.validCityName)

        //result
        val collectedResult = result.first()
        Assert.assertTrue(collectedResult is NetworkResult.Success)
        if (collectedResult is NetworkResult.Success)
            Assert.assertTrue(collectedResult.data.size == 2)
    }


    @Test
    fun `forecast error result on invalid city name`() = runBlockingTest {
        coEvery { repository.getForecast(FakeForecast.invalidCityName) } returns getFakeForeCastErrorNotFound()

        val result = useCase.execute(FakeForecast.invalidCityName)

        // result
        val collectedErrorList = result.first()
        Assert.assertTrue(collectedErrorList is NetworkResult.Error)
        if (collectedErrorList is NetworkResult.Error)
            Assert.assertTrue(collectedErrorList.errorCode == FakeForecast.errorNotFound)
    }

}