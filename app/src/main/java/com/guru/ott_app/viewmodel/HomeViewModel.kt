package com.guru.ott_app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guru.ott_app.api.Response
import com.guru.ott_app.model.Genres
import com.guru.ott_app.model.Result
import com.guru.ott_app.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class HomeViewModel(private val repository: MovieRepository) : ViewModel() {
    private val _trendingMovies =
        MutableStateFlow<Response<List<Result>>>(Response.Loading())
    val trendingMovies: StateFlow<Response<List<Result>>> = _trendingMovies

    private val _topRatedMovies =
        MutableStateFlow<Response<List<Result>>>(Response.Loading())
    val topRatedMovies: StateFlow<Response<List<Result>>> = _topRatedMovies

    private val _upComingMovies =
        MutableStateFlow<Response<List<Result>>>(Response.Loading())
    val upComingMovies: StateFlow<Response<List<Result>>> = _upComingMovies

    private val _genres = MutableStateFlow<Response<List<Genres>>>(Response.Loading())
    val genres: StateFlow<Response<List<Genres>>> = _genres

    // Property to store the selected genre ID
    private var selectedGenreId: Int? = null

    init {
        getTrendingMovies()
        getTopRatedMovies()
        getUpComingMovies()
        getMoviesGenres()
    }

    fun setSelectedGenre(genreId: Int?) {
        selectedGenreId = genreId
        // Fetch movies based on the selected genre ID
        getTrendingMovies()
        getTopRatedMovies()
        getUpComingMovies()
    }

    private fun getTrendingMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            _trendingMovies.value = Response.Loading()
            val response = repository.getTrendingMovies(selectedGenreId)
            if (response.isSuccessful) {
                _trendingMovies.value = Response.Success(response.body()?.results ?: emptyList())
            } else {
                _trendingMovies.value = Response.Error(response.message())
            }
        }
    }

    private fun getTopRatedMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            _topRatedMovies.value = Response.Loading()
            val response = repository.getTopRatedMovies(selectedGenreId)
            if (response.isSuccessful) {
                _topRatedMovies.value = Response.Success(response.body()?.results ?: emptyList())
            } else {
                _topRatedMovies.value = Response.Error(response.message())
            }
        }
    }

    private fun getUpComingMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            _upComingMovies.value = Response.Loading()
            val response = repository.getUpComingMovies(selectedGenreId)
            if (response.isSuccessful) {
                _upComingMovies.value = Response.Success(response.body()?.results ?: emptyList())
            } else {
                _upComingMovies.value = Response.Error(response.message())
            }
        }
    }

    private fun getMoviesGenres() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getGenres()
                _genres.value = if (response.isSuccessful) {
                    Response.Success(response.body()?.genres ?: emptyList())
                } else {
                    Response.Error("Failed to fetch genres")
                }
            } catch (e: Exception) {
                _genres.value = Response.Error("Exception Genres: ${e.message}")
            }
        }
    }
}
