package com.mufidz.nontonkuy.entity.response

import com.squareup.moshi.Json

data class CastResponse(
    val id: Int?,
    val name: String?,
    val character: String?,
    val gender: Int?,
    @Json(name = "profile_path") val profilePath: String?
)
