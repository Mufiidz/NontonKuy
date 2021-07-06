package com.mufidz.nontonkuy.data

import com.mufidz.nontonkuy.base.UseCaseResult
import com.mufidz.nontonkuy.entity.MovieEntity

sealed class DiscoverDataResult : UseCaseResult() {
    data class Success(val data: List<MovieEntity>) : DiscoverDataResult()
    data class Failed(val reason: String) : DiscoverDataResult()
}

sealed class NowPlayingDataResult : UseCaseResult() {
    data class Success(val data: List<MovieEntity>) : NowPlayingDataResult()
    data class Failed(val reason: String) : NowPlayingDataResult()
}