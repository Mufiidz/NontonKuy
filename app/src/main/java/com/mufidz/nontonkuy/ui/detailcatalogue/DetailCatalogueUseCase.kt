package com.mufidz.nontonkuy.ui.detailcatalogue

import com.mufidz.nontonkuy.base.BaseUseCase
import com.mufidz.nontonkuy.data.*
import com.mufidz.nontonkuy.entity.MovieEntity
import com.mufidz.nontonkuy.entity.CreditsEntity
import com.mufidz.nontonkuy.entity.response.CreditsResponse
import com.mufidz.nontonkuy.entity.response.MovieListResponse
import com.mufidz.nontonkuy.utils.*
import kotlinx.coroutines.withContext

class GetDetailMovie(
    private val dataManager: DataManagerRepository,
    private val dispatcher: DispatcherProvider
) : BaseUseCase<Int, RetrofitResult<MovieEntity>, DetailMovieDataResult>(dispatcher.dispatcherIO()) {

    override suspend fun execute(param: Int?): RetrofitResult<MovieEntity> =
        dataManager.loadDetailMovie(param ?: 0)

    override suspend fun RetrofitResult<MovieEntity>.transformToUseCaseResult(): DetailMovieDataResult =
        when (this) {
            is RetrofitResult.Success -> {
                val data = withContext(dispatcher.default()) {
                    this@transformToUseCaseResult.value.movieTransform()
                }
                DetailMovieDataResult.Success(data)
            }
            is RetrofitResult.Error -> DetailMovieDataResult.Failed(exception.message())
            is RetrofitResult.Exception ->
                DetailMovieDataResult.Failed(throwable.localizedMessage.orEmpty())
        }
}

class GetCreditsMovie(
    private val dataManager: DataManagerRepository,
    private val dispatcher: DispatcherProvider
) : BaseUseCase<Int, RetrofitResult<CreditsResponse>, DetailCreditsDataResult>(dispatcher.dispatcherIO()) {

    override suspend fun execute(param: Int?): RetrofitResult<CreditsResponse> =
        dataManager.loadCreditsMovie(param ?: 0)

    override suspend fun RetrofitResult<CreditsResponse>.transformToUseCaseResult(): DetailCreditsDataResult =
        when (this) {
            is RetrofitResult.Success -> {
                val cast = withContext(dispatcher.default()) {
                    this@transformToUseCaseResult.value.cast.castsTransforms()
                }
                val crew = withContext(dispatcher.default()) {
                    this@transformToUseCaseResult.value.crew.crewsTransforms()
                }

                val creditsEntity = CreditsEntity(cast, crew)
                DetailCreditsDataResult.Success(creditsEntity)
            }
            is RetrofitResult.Error -> DetailCreditsDataResult.Failed(exception.message())
            is RetrofitResult.Exception ->
                DetailCreditsDataResult.Failed(throwable.localizedMessage.orEmpty())
        }
}

class GetRecommendationsMovie(
    private val dataManager: DataManagerRepository,
    private val dispatcher: DispatcherProvider
) : BaseUseCase<Int, RetrofitResult<MovieListResponse>, ListMovieDataResult>(dispatcher.dispatcherIO()) {

    override suspend fun execute(param: Int?): RetrofitResult<MovieListResponse> =
        dataManager.loadRecommendationsMovie(param ?: 0)

    override suspend fun RetrofitResult<MovieListResponse>.transformToUseCaseResult(): ListMovieDataResult =
        when(this) {
            is RetrofitResult.Success -> {
                val data = withContext(dispatcher.default()) {
                    this@transformToUseCaseResult.value.results.moviesTransforms()
                }
                ListMovieDataResult.Success(data)
            }
            is RetrofitResult.Error -> ListMovieDataResult.Failed(exception.message())
            is RetrofitResult.Exception ->
                ListMovieDataResult.Failed(throwable.localizedMessage.orEmpty())
        }
}