package com.mufidz.nontonkuy.data

import com.mufidz.nontonkuy.BuildConfig
import com.mufidz.nontonkuy.entity.MovieEntity
import com.mufidz.nontonkuy.entity.response.CreditsResponse
import com.mufidz.nontonkuy.entity.response.MovieListResponse
import com.mufidz.nontonkuy.utils.awaitResult

interface DataManagerRepository {
    suspend fun loadDiscoverMovie(): RetrofitResult<MovieListResponse>
    suspend fun loadNowPlayingMovie(): RetrofitResult<MovieListResponse>
    suspend fun loadDetailMovie(id: Int): RetrofitResult<MovieEntity>
    suspend fun loadCreditsMovie(id: Int): RetrofitResult<CreditsResponse>
    suspend fun loadRecommendationsMovie(id: Int): RetrofitResult<MovieListResponse>
}

class DataManagerImpl(private val apiServices: ApiServices) : DataManagerRepository {

    private val apiKey = BuildConfig.MOVIEDB_APIKEY

    override suspend fun loadDiscoverMovie() =
        apiServices.getMovieDiscover(apiKey, 1, 2020).awaitResult()

    override suspend fun loadNowPlayingMovie() =
        apiServices.getMovieNowPlaying(apiKey, 1).awaitResult()

    override suspend fun loadDetailMovie(id: Int) =
        apiServices.getMovieDetail(id, apiKey).awaitResult()

    override suspend fun loadCreditsMovie(id: Int) =
        apiServices.getCreditsMovie(id, apiKey).awaitResult()

    override suspend fun loadRecommendationsMovie(id: Int) =
        apiServices.getRecommendationsMovie(id, apiKey).awaitResult()
}