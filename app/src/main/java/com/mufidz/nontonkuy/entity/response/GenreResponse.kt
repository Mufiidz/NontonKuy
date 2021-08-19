package com.mufidz.nontonkuy.entity.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GenreResponse(
    val id: Int? = 0,
    val name: String? = null
) : Parcelable