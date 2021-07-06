package com.mufidz.nontonkuy.ui.movie

import com.mufidz.nontonkuy.entity.MovieEntity

sealed class MovieSection {
    abstract val viewType: Int

    companion object {
        const val NOW_PLAYING_MOVIE = 1
        const val DISCOVER_MOVIE = 2
        const val FAVORITE_MOVIE = 3
        @JvmStatic
        fun getLayout(): MutableList<MovieSection> =
            mutableListOf(
                NowPlayingMovie(),
                DiscoverMovie(),
                FavoriteMovie
            )

        fun getDiscoverMovie(list: List<MovieEntity>) : MovieSection = DiscoverMovie(list)

        fun getNowPlaying(list: List<MovieEntity>) : MovieSection = NowPlayingMovie(list)
    }
}

data class DiscoverMovie(val discoverList: List<MovieEntity>? = null) : MovieSection() {
    override val viewType: Int get() = DISCOVER_MOVIE
}

data class NowPlayingMovie(val nowPlayingList: List<MovieEntity>? = null) : MovieSection() {
    override val viewType: Int get() = NOW_PLAYING_MOVIE
}

object FavoriteMovie : MovieSection() {
    override val viewType: Int get() = FAVORITE_MOVIE
}