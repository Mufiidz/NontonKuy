package com.mufidz.nontonkuy.entity.response

import com.mufidz.nontonkuy.entity.TvEntity
import com.squareup.moshi.Json

data class TvListResponse(
    val page: Int? = null,
    val results: List<TvEntity>? = null,
    @Json(name = "total_pages") val totalPages: Int? = null
)
