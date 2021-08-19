package com.mufidz.nontonkuy.entity

import android.os.Parcelable
import com.mufidz.nontonkuy.entity.response.GenreResponse
import com.mufidz.nontonkuy.utils.Const
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class MovieEntity(
    val id: Int? = 0,
    val title: String? = null,
    val overview: String? = null,
    val genres: List<GenreResponse> = emptyList(),
    val runtime: Int? = 0,
    @Json(name = "backdrop_path") val backdropPath: String? = null,
    @Json(name = "vote_count") val voteCount: Int? = null,
    @Json(name = "poster_path") val posterPath: String? = null,
    @Json(name = "release_date") val releaseDate: String? = null,
    @Json(name = "vote_average") val rating: Double? = null,
) : Parcelable {

    fun getListOfGenreNames(): String =
        genres.asSequence().map { it.name }.toList().joinToString(separator = ", ") {
            it?.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
                .orEmpty()
        }

    fun getUrlPosterImage() = posterPath?.takeIf { it.isNotEmpty() }?.let {
        "${Const.BASE_IMAGE_URL2}$it"
    }.orEmpty()

    fun getUrlBackDropImage() = backdropPath?.takeIf { it.isNotEmpty() }?.let {
        "${Const.BASE_IMAGE_URL2}$it"
    }.orEmpty()

    fun getReleaseDateText(): String {
        return if (releaseDate != null) {
            val cleanReleaseDate =
                if (releaseDate.contains("-")) releaseDate.replace("-", "")
                else releaseDate

            val year = cleanReleaseDate.take(4)
            val month = cleanReleaseDate.substring(4,6)
            val date = cleanReleaseDate.takeLast(2)
            "$date/$month/$year"
        } else "-"
    }

    fun getDurationText(): String {
        return if (runtime != null) {
            if (runtime >= 60) {
                val hour = runtime.div(60)
                val minutes = runtime.rem(60)
                "${hour}h ${minutes}m"
            } else {
                "${runtime}m"
            }
        } else "-"
    }

}
