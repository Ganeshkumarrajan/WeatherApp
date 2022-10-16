package com.anonymous.weatherapp.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.anonymous.weatherapp.domain.useCase.GetForecastUseCase
import com.anonymous.weatherapp.domain.fake_data.FakeForecast
import com.anonymous.weatherapp.domain.model.ForecastDomain
import com.anonymous.weatherapp.domain.utils.NetworkResult
import com.anonymous.weatherapp.presentation.viewModel.ForeCastViewModel
import com.anonymous.weatherapp.presentation.model.UIState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

import kotlinx.coroutines.test.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.DisplayName
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import kotlin.coroutines.ContinuationInterceptor

class ForeCastViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: ForeCastViewModel


    private val useCase = mockk<GetForecastUseCase>()

    @Before
    fun setup() {
        viewModel = ForeCastViewModel(useCase)
    }


    @DisplayName("")
    @Test
    fun testSuccessCase() = runBlocking {

        coEvery {
            useCase.execute(FakeForecast.validCityName)
        } returns getFLow()

        assert(viewModel.forecastState.value is UIState.Nothing)

        viewModel.getForeCastTo(FakeForecast.validCityName)

        assert(viewModel.forecastState.value is UIState.Success)
    }

    private suspend fun getFLow(): Flow<NetworkResult.Success<List<ForecastDomain>>> = flow {
        emit(
            NetworkResult.Success(
                listOf<ForecastDomain>
                    (ForecastDomain("", "", ""))
            )
        )
    }

}

@ExperimentalCoroutinesApi
class MainCoroutineRule : TestWatcher(), TestCoroutineScope by TestCoroutineScope() {

    override fun starting(description: Description) {
        super.starting(description)
        Dispatchers.setMain(this.coroutineContext[ContinuationInterceptor] as CoroutineDispatcher)
    }

    override fun finished(description: Description) {
        super.finished(description)
        Dispatchers.resetMain()
    }

}