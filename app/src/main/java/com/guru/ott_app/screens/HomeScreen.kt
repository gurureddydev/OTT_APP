package com.guru.ott_app.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.guru.ott_app.api.Response
import com.guru.ott_app.api.RetrofitInstance
import com.guru.ott_app.components.MovieCard
import com.guru.ott_app.components.MovieSlider
import com.guru.ott_app.components.SectionHeader
import com.guru.ott_app.components.ShimmerLoader
import com.guru.ott_app.model.Genres
import com.guru.ott_app.model.Result
import com.guru.ott_app.repository.MovieRepository
import com.guru.ott_app.ui.theme.dimens
import com.guru.ott_app.utils.Constants.isLoading
import com.guru.ott_app.viewmodel.HomeViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(homeViewModel: HomeViewModel) {
    val selectedGenreId by remember { mutableIntStateOf(-1) }
    val trendingMovies by homeViewModel.trendingMovies.collectAsState()
    val topRatedMovies by homeViewModel.topRatedMovies.collectAsState()
    val upComingMovies by homeViewModel.upComingMovies.collectAsState()
    val genresResponse by homeViewModel.genres.collectAsState()

    Scaffold(
        content = {
            HomeContent(
                trendingMovies,
                topRatedMovies,
                upComingMovies,
                genresResponse,
                selectedGenreId,
                homeViewModel
            )
        }
    )
}

@Composable
fun HomeContent(
    trendingMoviesResponse: Response<List<Result>>,
    topRatedMoviesResponse: Response<List<Result>>,
    upComingMoviesResponse: Response<List<Result>>,
    genresResponse: Response<List<Genres>>,
    selectedGenreId: Int,
    viewModel: HomeViewModel
) {
    if (isLoading(
            trendingMoviesResponse,
            topRatedMoviesResponse,
            upComingMoviesResponse,
            genresResponse
        )
    ) {
        ShimmerLoader()

    } else {
        val trendingItems = trendingMoviesResponse.data ?: emptyList()
        val topRatedItems = topRatedMoviesResponse.data ?: emptyList()
        val upComingItems = upComingMoviesResponse.data ?: emptyList()
        val genresItems = genresResponse.data ?: emptyList()

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            CategoriesList(
                genres = genresItems,
                selectedGenreId = selectedGenreId,
                viewModel = viewModel
            )

            Box(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                Column(
                    modifier = Modifier.padding(MaterialTheme.dimens.small2)
                ) {
                    MovieSlider(
                        movies = trendingItems,
                        modifier = Modifier
                            .padding(
                                horizontal = MaterialTheme.dimens.small1,
                                MaterialTheme.dimens.small1
                            )
                    )
                    DisplaySection("Trending Now", trendingItems)
                    DisplaySection("Upcoming Movies", upComingItems)
                    DisplaySection("Top Rated", topRatedItems)
                }
            }
        }
    }
}

@Composable
private fun DisplaySection(headerText: String, items: List<Result>) {
    SectionHeader(text = headerText)
    HorizontalMovieList(movieItems = items)
}

@Composable
fun HorizontalMovieList(movieItems: List<Result>?) {
    if (movieItems.isNullOrEmpty()) {
        Text(text = "No movies available")
    } else {
        LazyRow(
            modifier = Modifier.padding(horizontal = MaterialTheme.dimens.small1),
            contentPadding = PaddingValues(
                horizontal = MaterialTheme.dimens.small1,
                MaterialTheme.dimens.small1
            )
        ) {
            items(movieItems) { item ->
                MovieCard(
                    title = item.original_title,
                    imageUrl = item.poster_path,
                    modifier = Modifier
                        .width(MaterialTheme.dimens.width)
                        .height(MaterialTheme.dimens.height)
                        .padding(MaterialTheme.dimens.small1)
                )
            }
        }
    }
}

@Composable
fun CategoriesList(
    genres: List<Genres>?,
    selectedGenreId: Int,
    viewModel: HomeViewModel?
) {
    if (genres.isNullOrEmpty() || viewModel == null) {
        Text(text = "No genres available")
    } else {
        LazyRow(
            modifier = Modifier.padding(horizontal = MaterialTheme.dimens.small2),
            contentPadding = PaddingValues(
                horizontal = MaterialTheme.dimens.small1,
                vertical = MaterialTheme.dimens.small1
            )
        ) {
            items(genres) { genre ->
                Button(
                    onClick = { viewModel.setSelectedGenre(genre.id) },
                    modifier = Modifier.padding(end = MaterialTheme.dimens.small1),
                    enabled = selectedGenreId != genre.id // Disable button if already selected
                ) {
                    Text(
                        text = genre.name,
                        fontSize = MaterialTheme.dimens.textSizeSmall
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "Interactivity Preview")
@Preview(device = Devices.FOLDABLE)
@Preview(device = Devices.TV_1080p)
@Composable
fun PreviewHomeScreen() {
    val viewModel = HomeViewModel(MovieRepository(RetrofitInstance.getApiService()))
    HomeScreen(viewModel)
}
