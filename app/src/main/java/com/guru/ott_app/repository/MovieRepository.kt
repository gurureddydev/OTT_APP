package com.guru.ott_app.repository

import com.guru.ott_app.utils.Constants.API_KEY
import com.guru.ott_app.api.ApiService
import com.guru.ott_app.model.DataModel
import com.guru.ott_app.model.GenreResponse
import retrofit2.Response

class MovieRepository(private val apiService: ApiService) : GenresReo {
    suspend fun getTrendingMovies(genreId: Int?): Response<DataModel> {
        return if (genreId != null) {
            // Fetch trending movies filtered by genre
            apiService.getMoviesWithGenre(API_KEY, genreId)
        } else {
            // Fetch trending movies without filtering by genre
            apiService.getMovies(API_KEY)
        }
    }

    suspend fun getTopRatedMovies(genreId: Int?): Response<DataModel> {
        return if (genreId != null) {
            // Fetch top-rated movies filtered by genre
            apiService.getTopRatedMoviesWithGenre(API_KEY, genreId)
        } else {
            // Fetch top-rated movies without filtering by genre
            apiService.getTopRatedMovies(API_KEY)
        }
    }

    suspend fun getUpComingMovies(genreId: Int?): Response<DataModel> {
        return if (genreId != null) {
            // Fetch upcoming movies filtered by genre
            apiService.getUpComingMoviesWithGenre(API_KEY, genreId)
        } else {
            // Fetch upcoming movies without filtering by genre
            apiService.getUpComingMovies(API_KEY)
        }
    }

    override suspend fun getGenres(): Response<GenreResponse> {
        return apiService.getMovieGenres(API_KEY)
    }
}

interface GenresReo {
    suspend fun getGenres(): Response<GenreResponse>
}