package com.mufidz.nontonkuy.ui.listcatalogue

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.mufidz.nontonkuy.R
import com.mufidz.nontonkuy.base.BaseFragment
import com.mufidz.nontonkuy.databinding.FragmentAppbarListBinding
import com.mufidz.nontonkuy.ui.movie.MovieViewModel
import com.mufidz.nontonkuy.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentListCatalogue : BaseFragment<FragmentAppbarListBinding, MovieViewModel>(
    R.layout.fragment_appbar_list
) {

    private val args by navArgs<FragmentListCatalogueArgs>()

    override val viewModel by viewModel<MovieViewModel>()

    override val binding: FragmentAppbarListBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            toolbar.title = args.title
        }
    }
}