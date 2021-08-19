package com.mufidz.nontonkuy.ui.detailcatalogue

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.mufidz.nontonkuy.base.BaseViewModel
import com.mufidz.nontonkuy.base.UseCaseResult
import com.mufidz.nontonkuy.base.ViewSideEffect
import com.mufidz.nontonkuy.data.DetailCreditsDataResult
import com.mufidz.nontonkuy.data.DetailMovieDataResult
import com.mufidz.nontonkuy.data.ListMovieDataResult
import com.mufidz.nontonkuy.utils.callUseCase
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class DetailCatalogueViewModel(
    private val detailCatalogueRepository: DetailCatalogueRepository
) : BaseViewModel<DetailCatalogueViewState, DetailCatalogueAction, ViewSideEffect>(
    DetailCatalogueViewState()
) {

    override fun renderViewState(result: UseCaseResult?): DetailCatalogueViewState =
        when(result) {
            is DetailMovieDataResult -> result.mapDetailMovie()
            is DetailCreditsDataResult -> result.mapCreditsMovie()
            is ListMovieDataResult -> result.mapSimilarMovie()
            else -> getCurrentViewState()
        }

    override fun handleAction(action: DetailCatalogueAction): LiveData<UseCaseResult> =
        liveData(viewModelScope.coroutineContext) {
            when (action) {
                is DetailCatalogueAction.LoadDetailMovie -> {
                    val id = action.id
                    coroutineScope {
                        launch {
                            callUseCase(detailCatalogueRepository.getDetailMovie(id))
                            callUseCase(detailCatalogueRepository.getCreditsMovie(id))
                            callUseCase(detailCatalogueRepository.getSimilarMovie(id))
                        }
                    }
                }
            }
        }

    private fun DetailMovieDataResult.mapDetailMovie() : DetailCatalogueViewState =
        when(this) {
            is DetailMovieDataResult.Success -> getCurrentViewState().copy(
                isLoading = false, movieEntity = data
            )
            is DetailMovieDataResult.Failed -> getCurrentViewState().copy(
                isLoading = false, errorMessage = message
            )
        }

    private fun DetailCreditsDataResult.mapCreditsMovie() : DetailCatalogueViewState =
        when(this) {
            is DetailCreditsDataResult.Success -> getCurrentViewState().copy(
                isLoading = false, credits = creditsEntity
            )
            is DetailCreditsDataResult.Failed -> getCurrentViewState().copy(
                isLoading = false, errorMessage = message
            )
        }

    private fun ListMovieDataResult.mapSimilarMovie() : DetailCatalogueViewState =
        when(this) {
            is ListMovieDataResult.Success -> getCurrentViewState().copy(
                isLoading = false, listRecommend = data
            )
            is ListMovieDataResult.Failed -> getCurrentViewState().copy(
                isLoading = false, errorMessage = message
            )
        }
}