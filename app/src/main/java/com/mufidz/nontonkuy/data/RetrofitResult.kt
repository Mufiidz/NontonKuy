package com.mufidz.nontonkuy.data

import retrofit2.HttpException

sealed class RetrofitResult<out T> {
    data class Success<T>(val value: T): RetrofitResult<T>()
    data class Error(val exception: HttpException): RetrofitResult<Nothing>()
    data class Exception(val throwable: Throwable): RetrofitResult<Nothing>()
}