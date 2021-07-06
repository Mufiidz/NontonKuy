package com.mufidz.nontonkuy.ui.movie

import com.mufidz.nontonkuy.base.ViewAction

sealed class MovieAction : ViewAction {
    object LoadDataMovie : MovieAction()
}