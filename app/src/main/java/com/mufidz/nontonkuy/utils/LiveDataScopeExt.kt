package com.mufidz.nontonkuy.utils

import androidx.lifecycle.LiveDataScope
import com.mufidz.nontonkuy.base.UseCaseResult

suspend fun LiveDataScope<UseCaseResult>.callUseCase(block: UseCaseResult) {
    emit(block)
}