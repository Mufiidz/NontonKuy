package com.mufidz.nontonkuy.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CreditsEntity(
    val casts: List<CastEntity>? = emptyList(),
    val crews: List<CrewEntity>? = emptyList()
) : Parcelable
