package com.guru.ott_app.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import com.guru.ott_app.ui.theme.dimens
import com.guru.ott_app.utils.Constants

@Composable
fun MovieCard(title: String, imageUrl: String, modifier: Modifier = Modifier) {
    val posterUrl = "${Constants.BASE_URL_IMG}$imageUrl"
    Card(
        modifier = modifier.clip(RoundedCornerShape(MaterialTheme.dimens.small1)),
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(MaterialTheme.dimens.large)
                    .clip(RoundedCornerShape(MaterialTheme.dimens.small1))
                    .background(Color.Gray)
            ) {
                // Placeholder for the movie poster or image
                if (posterUrl.isNotEmpty()) {
                    AsyncImage(
                        url = posterUrl,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
                // Play icon
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = "Play",
                    tint = Color.White,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(MaterialTheme.dimens.medium2)
                        .background(Color.White.copy(alpha = 0.5f), shape = CircleShape)
                )
            }
            Column(
                modifier = Modifier
                    .padding(MaterialTheme.dimens.small1) // Increase padding for tablet view
                    .align(Alignment.BottomStart)
            ) {
                // Movie title
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 2, // Increase max lines for tablet view
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}
