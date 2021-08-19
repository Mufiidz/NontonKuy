package com.mufidz.nontonkuy.entity

import android.os.Parcelable
import com.mufidz.nontonkuy.utils.Const
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvEntity(
    val id: Int? = 0,
    val name: String? = null,
    val overview: String? = null,
    @Json(name = "backdrop_path") val backdropPath: String? = null,
    @Json(name = "vote_count") val voteCount: Int? = null,
    @Json(name = "first_air_date") val releaseDate: String? = null,
    @Json(name = "poster_path") val posterPath: String? = null,
    @Json(name = "vote_average") val rating: Double? = null,
) : Parcelable {

    fun getUrlPosterImage() = posterPath?.takeIf { it.isNotEmpty() }?.let {
        "${Const.BASE_IMAGE_URL2}$it"
    }.orEmpty()

    fun getUrlBackDropImage() = backdropPath?.takeIf { it.isNotEmpty() }?.let {
        "${Const.BASE_IMAGE_URL2}$it"
    }.orEmpty()
}
