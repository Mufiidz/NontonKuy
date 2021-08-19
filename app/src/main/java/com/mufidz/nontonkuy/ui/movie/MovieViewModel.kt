package com.mufidz.nontonkuy.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.mufidz.nontonkuy.base.BaseViewModel
import com.mufidz.nontonkuy.base.UseCaseResult
import com.mufidz.nontonkuy.base.ViewSideEffect
import com.mufidz.nontonkuy.data.ListMovieDataResult
import com.mufidz.nontonkuy.data.NowPlayingDataResult
import com.mufidz.nontonkuy.entity.MovieEntity
import com.mufidz.nontonkuy.utils.callUseCase
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MovieViewModel(private val movieRepository: MovieRepository) :
    BaseViewModel<DataState<MovieEntity>, MovieAction, ViewSideEffect>(initState = DataState()) {

    override fun renderViewState(result: UseCaseResult?): DataState<MovieEntity> {
        return when (result) {
            is ListMovieDataResult -> result.mapDiscoverMovie()
            is NowPlayingDataResult -> result.mapNowPlaying()
            else -> getCurrentViewState()
        }
    }

    override fun handleAction(action: MovieAction): LiveData<UseCaseResult> =
        liveData(viewModelScope.coroutineContext) {
            when (action) {
                is MovieAction.LoadDataMovie -> {
                    delay(250)
                    coroutineScope {
                        launch {
                            callUseCase(movieRepository.getDiscoverMovie())
                            callUseCase(movieRepository.getNowPlaying())
                        }
                    }
                }
            }
        }


    private fun ListMovieDataResult.mapDiscoverMovie(): DataState<MovieEntity> {
        return when (this) {
            is ListMovieDataResult.Success -> getCurrentViewState().copy(
                discoverList = data, isLoading = false
            )
            is ListMovieDataResult.Failed -> getCurrentViewState().copy(
                errorMessage = message, isLoading = false
            )
        }
    }

    private fun NowPlayingDataResult.mapNowPlaying(): DataState<MovieEntity> {
        return when (this) {
            is NowPlayingDataResult.Success -> getCurrentViewState().copy(
                nowPlayingList = data, isLoading = false
            )
            is NowPlayingDataResult.Failed -> getCurrentViewState().copy(
                errorMessage = message, isLoading = false
            )
        }
    }
}