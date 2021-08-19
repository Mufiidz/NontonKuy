package com.mufidz.nontonkuy.ui.movie

import com.mufidz.nontonkuy.data.ListMovieDataResult
import com.mufidz.nontonkuy.data.NowPlayingDataResult

interface MovieRepository {
    suspend fun getDiscoverMovie(): ListMovieDataResult
    suspend fun getNowPlaying() : NowPlayingDataResult
}

class MovieRepositoryImpl(
    private val discoverMovie: GetDiscoverMovie,
    private val nowPlaying: GetNowPlaying
) : MovieRepository {

    override suspend fun getDiscoverMovie() = discoverMovie.getResult()

    override suspend fun getNowPlaying() = nowPlaying.getResult()

}