package com.mufidz.nontonkuy.ui.favorite

import android.os.Bundle
import android.view.View
import com.mufidz.nontonkuy.R
import com.mufidz.nontonkuy.base.BaseFragment
import com.mufidz.nontonkuy.databinding.FragmentFavoriteBinding
import com.mufidz.nontonkuy.ui.movie.MovieViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding, MovieViewModel>(
    R.layout.fragment_favorite, FragmentFavoriteBinding::bind
) {

    override val viewModel by viewModel<MovieViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}