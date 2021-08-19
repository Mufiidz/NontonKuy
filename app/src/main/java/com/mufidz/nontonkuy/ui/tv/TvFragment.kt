package com.mufidz.nontonkuy.ui.tv

import com.mufidz.nontonkuy.R
import com.mufidz.nontonkuy.base.BaseFragment
import com.mufidz.nontonkuy.databinding.FragmentAppbarListBinding
import com.mufidz.nontonkuy.ui.movie.MovieViewModel
import com.mufidz.nontonkuy.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvFragment :
    BaseFragment<FragmentAppbarListBinding, MovieViewModel>(R.layout.fragment_appbar_list) {

    override val viewModel by viewModel<MovieViewModel>()

    override val binding: FragmentAppbarListBinding by viewBinding()

}