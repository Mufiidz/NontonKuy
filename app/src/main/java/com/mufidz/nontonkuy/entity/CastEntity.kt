package com.mufidz.nontonkuy.entity

import android.os.Parcelable
import com.mufidz.nontonkuy.utils.Const
import kotlinx.parcelize.Parcelize

@Parcelize
data class CastEntity(
    val id: Int,
    val name: String,
    val characterName: String,
    val gender: Int,
    val profilePath: String
) : Parcelable {

    fun getFullPathProfileUrl(): String = profilePath.takeIf { it.isNotBlank() }?.let {
        "${Const.BASE_IMAGE_URL}${Const.PROFILE_PICTURE_SIZE}$it"
    }.orEmpty()
}
