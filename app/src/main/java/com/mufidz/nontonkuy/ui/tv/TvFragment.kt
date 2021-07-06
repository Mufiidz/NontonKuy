package com.mufidz.nontonkuy.ui.tv

import android.os.Bundle
import android.view.View
import com.mufidz.nontonkuy.R
import com.mufidz.nontonkuy.base.BaseFragment
import com.mufidz.nontonkuy.databinding.FragmentAppbarListBinding
import com.mufidz.nontonkuy.ui.movie.MovieViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvFragment : BaseFragment<FragmentAppbarListBinding, MovieViewModel>(
    R.layout.fragment_appbar_list, FragmentAppbarListBinding::bind
) {

    override val viewModel by viewModel<MovieViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}