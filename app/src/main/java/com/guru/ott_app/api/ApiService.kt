package com.guru.ott_app.api

import com.guru.ott_app.model.DataModel
import com.guru.ott_app.model.GenreResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("api_key") apiKey: String): Response<DataModel>

    @GET("discover/movie")
    suspend fun getMovies(@Query("api_key") apiKey: String): Response<DataModel>

    @GET("trending/movie/day")
    suspend fun getUpComingMovies(@Query("api_key") apiKey: String): Response<DataModel>


    @GET("genre/movie/list")
    suspend fun getMovieGenres(@Query("api_key") apiKey: String): Response<GenreResponse>

    @GET("discover/movie")
    suspend fun getMoviesWithGenre(
        @Query("api_key") apiKey: String,
        @Query("with_genres") genreId: Int
    ): Response<DataModel>

    @GET("movie/top_rated")
    suspend fun getTopRatedMoviesWithGenre(
        @Query("api_key") apiKey: String,
        @Query("with_genres") genreId: Int
    ): Response<DataModel>

    @GET("trending/movie/day")
    suspend fun getUpComingMoviesWithGenre(
        @Query("api_key") apiKey: String,
        @Query("with_genres") genreId: Int
    ): Response<DataModel>

//    @GET("movie/{movie_id}")
//    suspend fun getMovieDetails(
//        @Path("movie_id") id: Int, @Query("api_key") apiKey: String
//    ): Response<DetailResponse>
//
//    @GET("movie/{movie_id}/credits")
//    suspend fun getMovieCast(
//        @Path("movie_id") id: Int, @Query("api_key") apiKey: String
//    ): Response<CastResponse>
//
//    @GET("movie/{movie_id}/videos")
//    suspend fun getMovieVideos(
//        @Path("movie_id") id: Int, @Query("api_key") apiKey: String
//    ): Response<VideoResponse>
}