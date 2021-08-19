package com.mufidz.nontonkuy.entity.response

import com.squareup.moshi.Json

data class CrewResponse(
    val id: Int?,
    val name: String?,
    val job: String?,
    val gender: Int?,
    @Json(name = "profile_path") val profilePath: String?
)
