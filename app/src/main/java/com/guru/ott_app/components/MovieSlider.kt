package com.guru.ott_app.components

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.guru.ott_app.model.Result
import kotlinx.coroutines.delay
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.guru.ott_app.ui.theme.dimens
import com.guru.ott_app.utils.Constants.BASE_URL_IMG

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MovieSlider(
    movies: List<Result>,
    modifier: Modifier = Modifier,
) {

    Box(modifier = modifier
        .fillMaxSize()
        .clip(RoundedCornerShape(4))) {
        AutoSlidingCarousel(
            modifier = Modifier.fillMaxSize(),
            itemsCount = movies.size,
            itemContent = { index ->
                val movie = movies[index]
                val backdropPath = movie.backdrop_path
                val posterUrl = BASE_URL_IMG + backdropPath
                val title = movie.title
                Box(modifier = Modifier.fillMaxWidth()) {
                    AsyncImage(
                        model = posterUrl,
                        contentDescription = title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(
                                min = MaterialTheme.dimens.large,
                                max = MaterialTheme.dimens.largeHeight
                            ) // Adjust height for both tablet and mobile
                            .clip(RoundedCornerShape(10))
                            .aspectRatio(16f / 9f),
                        contentScale = ContentScale.FillBounds
                    )
                    Box(
                        modifier = Modifier
                            .background(Color.White.copy(alpha = 0.5f), shape = CircleShape)
                            .padding()
                            .align(Alignment.Center)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(MaterialTheme.dimens.small1)
                        ) {
                            Icon(
                                imageVector = Icons.Default.PlayArrow,
                                contentDescription = "Play",
                                tint = Color.Black,
                                modifier = Modifier.size(MaterialTheme.dimens.medium1)
                            )
                            Text(
                                text = "Watch Now",
                                fontSize = MaterialTheme.dimens.textSizeSmall,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomStart)
                            .padding(MaterialTheme.dimens.small1)
                    ) {
                        Column {
                            Text(
                                text = movie.title,
                                fontSize = MaterialTheme.dimens.textSizeMedium,
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = movie.original_title,
                                fontSize = MaterialTheme.dimens.textSizeSmall,
                                color = Color.White.copy(alpha = 0.5f),
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        )
    }
}

@Preview(showBackground = true, name = "Interactivity Preview")
@Preview(device = Devices.FOLDABLE)
@Preview(device = Devices.TV_1080p)
@Composable
fun MovieSliderPreview() {
    val movies = listOf(
        Result(
            adult = false,
            backdrop_path = "",
            genre_ids = listOf(1, 2),
            id = 1,
            original_language = "en",
            original_title = "Original Title",
            overview = "Overview",
            popularity = 7.5,
            poster_path = "your_poster_path_url_here",
            release_date = "2024-03-27",
            title = "Title",
            video = false,
            vote_average = 7.8,
            vote_count = 100
        )
    )
    MovieSlider(movies = movies)
}
