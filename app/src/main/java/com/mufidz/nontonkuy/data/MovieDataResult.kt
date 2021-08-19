package com.mufidz.nontonkuy.data

import com.mufidz.nontonkuy.base.UseCaseResult
import com.mufidz.nontonkuy.entity.MovieEntity
import com.mufidz.nontonkuy.entity.CreditsEntity

sealed class ListMovieDataResult : UseCaseResult() {
    data class Success(val data: List<MovieEntity>) : ListMovieDataResult()
    data class Failed(val message: String) : ListMovieDataResult()
}

sealed class NowPlayingDataResult : UseCaseResult() {
    data class Success(val data: List<MovieEntity>) : NowPlayingDataResult()
    data class Failed(val message: String) : NowPlayingDataResult()
}

sealed class DetailMovieDataResult : UseCaseResult() {
    data class Success(val data: MovieEntity) : DetailMovieDataResult()
    data class Failed(val message: String) : DetailMovieDataResult()
}

sealed class DetailCreditsDataResult : UseCaseResult() {
    data class Success(val creditsEntity: CreditsEntity) :
        DetailCreditsDataResult()
    data class Failed(val message: String) : DetailCreditsDataResult()
}