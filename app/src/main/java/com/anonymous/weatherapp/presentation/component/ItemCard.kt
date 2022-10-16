package com.anonymous.weatherapp.presentation.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.anonymous.weatherapp.presentation.model.ForeCastUI
import androidx.compose.foundation.Image
import androidx.compose.ui.Alignment


@Composable
fun ItemCard(foreCastUI: ForeCastUI) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Column(modifier = Modifier.padding(10.dp)) {
                TitleText(foreCastUI.temp)
                SubTitleText(foreCastUI.date)
            }
            SmallImage(foreCastUI.imagePath)
        }
    }
}

@Composable
private fun TitleText(value: String) {
    Text(
        text = "$value°",
        style = MaterialTheme.typography.h3,
        color = MaterialTheme.colors.primary
    )
}

@Composable
private fun SubTitleText(value: String) {
    Text(
        text = value,
        style = MaterialTheme.typography.h5,
        color = MaterialTheme.colors.secondary
    )
}


@Composable
private fun SmallImage(imagePath: String) {
    val resource = rememberImagePainter(data = imagePath)

    Image(
        painter = resource,
        contentDescription = null,

        modifier = Modifier.size(128.dp)
    )
}

@Preview
@Composable
fun PreViewItemCard() {
    MaterialTheme {
        ItemCard(ForeCastUI("28", "Mon 8", "1d"))
    }
}
