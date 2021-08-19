package com.mufidz.nontonkuy.entity

import android.os.Parcelable
import com.mufidz.nontonkuy.utils.Const
import kotlinx.parcelize.Parcelize

@Parcelize
data class CrewEntity(
    val id: Int,
    val name: String,
    val job: String,
    val gender: Int,
    val profilePath: String
) : Parcelable {

    fun getFullPathProfileUrl(): String = profilePath.takeIf { it.isNotBlank() }?.let {
        "${Const.BASE_IMAGE_URL2}${Const.PROFILE_PICTURE_SIZE}$it"
    }.orEmpty()
}
