package com.mufidz.nontonkuy.ui.detailcatalogue

import com.mufidz.nontonkuy.data.DetailCreditsDataResult
import com.mufidz.nontonkuy.data.DetailMovieDataResult
import com.mufidz.nontonkuy.data.ListMovieDataResult

interface DetailCatalogueRepository {
    suspend fun getDetailMovie(id: Int) : DetailMovieDataResult
    suspend fun getCreditsMovie(id: Int) : DetailCreditsDataResult
    suspend fun getSimilarMovie(id: Int) : ListMovieDataResult
}

class DetailCatalogueRepositoryImpl(
    private val detailMovie: GetDetailMovie,
    private val creditsMovie: GetCreditsMovie,
    private val similarMovie: GetRecommendationsMovie
) : DetailCatalogueRepository {

    override suspend fun getDetailMovie(id: Int) = detailMovie.getResult(id)

    override suspend fun getCreditsMovie(id: Int) = creditsMovie.getResult(id)

    override suspend fun getSimilarMovie(id: Int) = similarMovie.getResult(id)

}

