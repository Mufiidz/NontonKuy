package com.mufidz.nontonkuy.ui.movie

import com.mufidz.nontonkuy.base.BaseUseCase
import com.mufidz.nontonkuy.data.*
import com.mufidz.nontonkuy.entity.response.MovieListResponse
import com.mufidz.nontonkuy.utils.DispatcherProvider
import com.mufidz.nontonkuy.utils.moviesTransforms
import kotlinx.coroutines.withContext

class GetDiscoverMovie(
    private val dataManager: DataManagerRepository,
    private val dispatcher: DispatcherProvider
) : BaseUseCase<Nothing, RetrofitResult<MovieListResponse>, ListMovieDataResult>(
        dispatcher.dispatcherIO()
) {

    override suspend fun execute(param: Nothing?): RetrofitResult<MovieListResponse> =
        dataManager.loadDiscoverMovie()

    override suspend fun RetrofitResult<MovieListResponse>.transformToUseCaseResult(): ListMovieDataResult {
        return when (this) {
            is RetrofitResult.Success -> {
                val data = withContext(dispatcher.default()) {
                    this@transformToUseCaseResult.value.results.moviesTransforms()
                }
                ListMovieDataResult.Success(data)
            }
            is RetrofitResult.Error -> ListMovieDataResult.Failed(this.exception.message())
            is RetrofitResult.Exception ->
                ListMovieDataResult.Failed(this.throwable.localizedMessage.orEmpty())
        }
    }
}

class GetNowPlaying(
    private val dataManager: DataManagerRepository,
    private val dispatcher: DispatcherProvider
) : BaseUseCase<Nothing, RetrofitResult<MovieListResponse>, NowPlayingDataResult>(
    dispatcher.dispatcherIO()
) {
    override suspend fun execute(param: Nothing?): RetrofitResult<MovieListResponse> =
        dataManager.loadNowPlayingMovie()

    override suspend fun RetrofitResult<MovieListResponse>.transformToUseCaseResult(): NowPlayingDataResult {
        return when(this) {
            is RetrofitResult.Success -> {
                val data = withContext(dispatcher.default()) {
                    this@transformToUseCaseResult.value.results.moviesTransforms()
                }
                NowPlayingDataResult.Success(data)
            }
            is RetrofitResult.Error -> NowPlayingDataResult.Failed(this.exception.message())
            is RetrofitResult.Exception ->
                NowPlayingDataResult.Failed(this.throwable.localizedMessage.orEmpty())
        }
    }

}