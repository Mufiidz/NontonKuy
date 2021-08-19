package com.mufidz.nontonkuy.data

import com.mufidz.nontonkuy.entity.MovieEntity
import com.mufidz.nontonkuy.entity.TvEntity
import com.mufidz.nontonkuy.entity.response.CreditsResponse
import com.mufidz.nontonkuy.entity.response.TvListResponse
import com.mufidz.nontonkuy.entity.response.MovieListResponse
import com.mufidz.nontonkuy.utils.Const
import com.mufidz.nontonkuy.utils.Const.CREDITS_MOVIE_ENDPOINT
import com.mufidz.nontonkuy.utils.Const.MOVIE_DETAIL_ENDPOINT
import com.mufidz.nontonkuy.utils.Const.MOVIE_DISCOVER_ENDPOINT
import com.mufidz.nontonkuy.utils.Const.MOVIE_NOWPLAYING_ENDPOINT
import com.mufidz.nontonkuy.utils.Const.RECOMMENDATIONS_MOVIE_ENDPOINT
import com.mufidz.nontonkuy.utils.Const.TV_DETAIL_ENDPOINT
import com.mufidz.nontonkuy.utils.Const.TV_DISCOVER_ENDPOINT
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {

    @GET(MOVIE_DISCOVER_ENDPOINT)
    fun getMovieDiscover(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        @Query("year") year: Int,
        @Query("language") language: String = Const.ID
    ): Call<MovieListResponse>

    @GET(TV_DISCOVER_ENDPOINT)
    fun getTvDiscover(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        @Query("first_air_date_year") year: Int,
        @Query("language") language: String = Const.ID
    ): Call<TvListResponse>

    @GET(MOVIE_NOWPLAYING_ENDPOINT)
    fun getMovieNowPlaying(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        @Query("language") language: String = Const.ID
    ): Call<MovieListResponse>

    @GET(MOVIE_DETAIL_ENDPOINT)
    fun getMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String = Const.US
    ): Call<MovieEntity>

    @GET(TV_DETAIL_ENDPOINT)
    fun getTvDetail(
        @Path("tv_id") tvId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String = Const.ID
    ): Call<TvEntity>

    @GET(CREDITS_MOVIE_ENDPOINT)
    fun getCreditsMovie(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Call<CreditsResponse>

    @GET(RECOMMENDATIONS_MOVIE_ENDPOINT)
    fun getRecommendationsMovie(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Call<MovieListResponse>
}