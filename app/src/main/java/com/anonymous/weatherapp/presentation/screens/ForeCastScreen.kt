package com.anonymous.weatherapp.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.anonymous.weatherapp.presentation.component.ItemCard
import com.anonymous.weatherapp.presentation.component.SearchBarUI
import com.anonymous.weatherapp.presentation.model.ForeCastUI
import com.anonymous.weatherapp.presentation.viewModel.ForeCastViewModel
import com.anonymous.weatherapp.presentation.model.UIState


@Composable
fun ForeCastScreen() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        val foreCastViewModel: ForeCastViewModel = hiltViewModel()

        SearchBarUI(onSearchClicked = {
            foreCastViewModel.getForeCastTo(it)
        })

        SetObserverForResult(foreCastViewModel)
    }
}

@Composable
private fun SetObserverForResult(foreCastViewModel: ForeCastViewModel) {
    when (val data = foreCastViewModel.forecastState.collectAsState().value) {
        is UIState.Success -> ShowResult(data.data)
        is UIState.Loading -> ProgressIndicator()
        is UIState.Error -> Error()
        else -> {}
    }
}

@Composable
fun ShowResult(list: List<ForeCastUI>) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(18.dp),
    ) {
        items(list) { item ->
            ItemCard(foreCastUI = item)
        }
    }
}

@Composable
private fun ProgressIndicator() {
    CircularProgressIndicator(modifier = Modifier.size(20.dp))
}

@Composable
private fun Error() {
    Text(
        text = stringResource(id = com.anonymous.weatherapp.R.string.please_try_again),
        style = MaterialTheme.typography.h3
    )
}
