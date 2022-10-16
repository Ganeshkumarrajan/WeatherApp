package com.anonymous.weatherapp.presentation.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue

@Composable
fun SearchBarUI(
    onSearchClicked: (String) -> (Unit)
) {
    Box {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {

            SearchEditText(onSearchClicked)
        }
    }
}

@Composable
private fun SearchEditText(onSearchClicked: (String) -> (Unit)) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        val textState = remember { mutableStateOf(TextFieldValue()) }
        TextField(
            placeholder = {
                Text(stringResource(id = com.anonymous.weatherapp.R.string.tyep_city_name))
            },

            maxLines = 1,
            value = textState.value,
            onValueChange = { textState.value = it }
        )

        Button(onClick = {
            if (textState.value.text.isNotEmpty())
                onSearchClicked.invoke(textState.value.text)
        }) {
            Text(text = stringResource(id = com.anonymous.weatherapp.R.string.search))
        }
    }
}

