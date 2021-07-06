package com.mufidz.nontonkuy.entity

import android.os.Parcelable
import androidx.annotation.Keep
import com.mufidz.nontonkuy.utils.Const
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieEntity(
    val id: Int? = 0,
    val title: String? = null,
    val overview: String? = null,
    @Json(name = "backdrop_path") val backdropPath: String? = null,
    @Json(name = "vote_count") val voteCount: Int? = null,
    @Json(name = "poster_path") val posterPath: String? = null,
    @Json(name = "release_date") val releaseDate: String? = null,
    @Json(name = "vote_average") val rating: Double? = null,
) : Parcelable {

    fun getUrlPosterImage() = posterPath?.takeIf { it.isNotEmpty() }?.let {
        "${Const.BASE_IMAGE_URL}$it"
    }.orEmpty()

    fun getUrlBackDropImage() = backdropPath?.takeIf { it.isNotEmpty() }?.let {
        "${Const.BASE_IMAGE_URL}$it"
    }.orEmpty()

}
