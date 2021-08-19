package com.mufidz.nontonkuy.ui.detailcatalogue

import com.mufidz.nontonkuy.base.ViewAction
import com.mufidz.nontonkuy.base.ViewState
import com.mufidz.nontonkuy.entity.MovieEntity
import com.mufidz.nontonkuy.entity.CreditsEntity

data class DetailCatalogueViewState(
    val credits: CreditsEntity? = null,
    val movieEntity: MovieEntity? = MovieEntity(),
    val listRecommend: List<MovieEntity>? = emptyList(),
    val errorMessage: String? = null,
    val isLoading: Boolean = true
) : ViewState()

sealed class DetailCatalogueAction : ViewAction {
    data class LoadDetailMovie(val id: Int) : DetailCatalogueAction()
}

