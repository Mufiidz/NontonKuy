package com.mufidz.nontonkuy.ui.detailcatalogue

import com.mufidz.nontonkuy.base.Section
import com.mufidz.nontonkuy.entity.MovieEntity
import com.mufidz.nontonkuy.entity.CreditsEntity

sealed class DetailCatalogueSection : Section() {

    companion object {
        const val DETAIL_HEADER_MOVIE = 1
        const val DETAIL_OVERVIEW = 2
        const val DETAIL_CAST = 3
        const val DETAIL_RECOMMENDATIONS = 4

        @JvmStatic
        fun getLayoutMovie(): MutableList<DetailCatalogueSection> =
            mutableListOf(
                DetailHeaderMovie(),
                DetailOverview(),
                DetailCredits(),
                DetailRecommendations()
            )

        fun getHeaderDetailMovie(movieEntity: MovieEntity) = DetailHeaderMovie(movieEntity)

        fun getOverviewDetailCatalogue(overview: String) = DetailOverview(overview)

        fun getCreditsDetailCatalogue(credits: CreditsEntity) = DetailCredits(credits)

        fun getRecommendDetailCatalogue(listRecommend: List<MovieEntity>) = DetailRecommendations(listRecommend)
    }
}

data class DetailHeaderMovie(val movieEntity: MovieEntity? = null) : DetailCatalogueSection() {
    override val viewType: Int get() = DETAIL_HEADER_MOVIE
}

data class DetailOverview(val overview: String? = null) : DetailCatalogueSection() {
    override val viewType: Int get() = DETAIL_OVERVIEW
}

data class DetailCredits(val credits: CreditsEntity? = null) : DetailCatalogueSection() {
    override val viewType: Int get() = DETAIL_CAST
}

data class DetailRecommendations(val listRecommend: List<MovieEntity>? = null) : DetailCatalogueSection() {
    override val viewType: Int get() = DETAIL_RECOMMENDATIONS
}
