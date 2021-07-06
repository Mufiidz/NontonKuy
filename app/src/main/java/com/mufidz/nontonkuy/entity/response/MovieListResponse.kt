package com.mufidz.nontonkuy.entity.response

import com.mufidz.nontonkuy.entity.MovieEntity
import com.squareup.moshi.Json

data class MovieListResponse(
    val page: Int? = null,
    val results: List<MovieEntity>? = null,
    @Json(name = "total_pages") val totalPages: Int? = null
)
