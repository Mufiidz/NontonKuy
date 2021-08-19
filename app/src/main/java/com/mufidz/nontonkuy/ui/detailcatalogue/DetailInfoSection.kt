package com.mufidz.nontonkuy.ui.detailcatalogue

import com.mufidz.nontonkuy.base.Section

sealed class DetailInfoSection : Section() {

    companion object {
        const val INFO_RATING_MOVIE = 1
        const val INFO_RELEASE_MOVIE = 2
        const val INFO_RUNTIME_MOVIE = 3

        @JvmStatic
        fun getLayoutInfoMovie(): MutableList<DetailInfoSection> =
            mutableListOf(
                InfoRatingMovie(),
                InfoReleaseMovie(),
                InfoRuntimeMovie()
            )

        fun getInfoRating(rating: Double?, voteCount: Int?) = InfoRatingMovie(rating, voteCount)

        fun getInfoRelease(release: String) = InfoReleaseMovie(release)

        fun getInfoRuntime(runtime: String) = InfoRuntimeMovie(runtime)
    }
}

data class InfoRatingMovie(val rating: Double? = 0.0, val voteCount: Int? = 0) :
    DetailInfoSection() {
    override val viewType: Int get() = INFO_RATING_MOVIE
}

data class InfoReleaseMovie(val release: String? = null) : DetailInfoSection() {
    override val viewType: Int get() = INFO_RELEASE_MOVIE
}

data class InfoRuntimeMovie(val runtime: String? = null) : DetailInfoSection() {
    override val viewType: Int get() = INFO_RUNTIME_MOVIE
}