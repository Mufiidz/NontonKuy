package com.mufidz.nontonkuy.ui.favorite

import com.mufidz.nontonkuy.R
import com.mufidz.nontonkuy.base.BaseFragment
import com.mufidz.nontonkuy.databinding.FragmentFavoriteBinding
import com.mufidz.nontonkuy.ui.movie.MovieViewModel
import com.mufidz.nontonkuy.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding, MovieViewModel>(R.layout.fragment_favorite
) {

    override val viewModel by viewModel<MovieViewModel>()

    override val binding: FragmentFavoriteBinding by viewBinding()

}