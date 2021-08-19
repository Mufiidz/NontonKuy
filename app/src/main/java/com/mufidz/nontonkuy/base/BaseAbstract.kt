package com.mufidz.nontonkuy.base

abstract class UseCaseResult

abstract class ViewState

abstract class ViewSideEffect : ViewState()

abstract class Section {
    abstract val viewType: Int
}