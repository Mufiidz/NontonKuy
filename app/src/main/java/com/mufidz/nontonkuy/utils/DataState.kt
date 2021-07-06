package com.mufidz.nontonkuy.utils

import com.mufidz.nontonkuy.base.ViewState

data class DataState<T>(
    val discoverList: List<T> = emptyList(),
    val nowPlayingList: List<T> = emptyList(),
    val errorMessage: String? = null,
    val isLoading: Boolean = true
) : ViewState()