package com.anonymous.weatherapp.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anonymous.weatherapp.domain.useCase.GetForecastUseCase
import com.anonymous.weatherapp.domain.utils.NetworkResult
import com.anonymous.weatherapp.presentation.model.ForeCastUI
import com.anonymous.weatherapp.presentation.model.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ForeCastViewModel @Inject constructor(
    private val useCase: GetForecastUseCase
) : ViewModel() {
    private val _forecastState = MutableStateFlow<UIState<List<ForeCastUI>>>(UIState.Nothing())
    val forecastState = _forecastState

    fun getForeCastTo(cityName: String) {
        viewModelScope.launch {
            forecastState.value = UIState.Loading()
            useCase.execute(cityName).collect {
                when (it) {
                    is NetworkResult.Success -> {
                        forecastState.value = UIState.Success(it.data.map { data ->
                            ForeCastUI(data.temp, data.date, data.temperatureImagePath)
                        })
                    }
                    is NetworkResult.Error -> {
                        forecastState.value = UIState.Error()
                    }
                }

            }

        }
    }

}
