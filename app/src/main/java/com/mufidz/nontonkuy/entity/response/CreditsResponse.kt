package com.mufidz.nontonkuy.entity.response

data class CreditsResponse(
    val cast: List<CastResponse>? = emptyList(),
    val crew: List<CrewResponse>? = emptyList()
)
