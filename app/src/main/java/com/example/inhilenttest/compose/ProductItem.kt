package com.example.inhilenttest.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.inhilenttest.R

@Composable
fun ProductElement(
    imgUrl: String,
    title: String,
    brand: String,
    prize: String,
    modifier: Modifier = Modifier
) {

    Surface(modifier.padding(2.dp)) {
        Card(
            border = BorderStroke(width = 2.dp, color = Color.Gray),
            shape = MaterialTheme.shapes.medium,
            modifier = modifier,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
            )
        ) {
            Column(modifier = Modifier) {
                AsyncImage(
                    model = imgUrl,
                    contentDescription = null,
                    modifier = Modifier,
                    error = painterResource(id = R.drawable.picture)
                )
                Surface(
                    modifier = Modifier
                        .paddingFromBaseline(
                            top = 16.dp, bottom = 16.dp
                        )
                        .padding(horizontal = 8.dp)
                        .align(alignment = Alignment.Start)
                ) {
                    Column {
                        Text(
                            text = title,
                        )
                        Text(text = "brand: $brand")
                        Text(text = "prize: $prize")
                    }
                }
            }
        }
    }
}
