package com.mufidz.nontonkuy.ui.movie

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mufidz.nontonkuy.R
import com.mufidz.nontonkuy.base.BaseFragment
import com.mufidz.nontonkuy.base.ItemListener
import com.mufidz.nontonkuy.databinding.FragmentAppbarListBinding
import com.mufidz.nontonkuy.ui.DetailActivity
import com.mufidz.nontonkuy.utils.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFragment : BaseFragment<FragmentAppbarListBinding, MovieViewModel>(
    R.layout.fragment_appbar_list
), ItemListener<Any> {

    private val movieAdapter by lazy { MovieHomeAdapter() }

    override val binding: FragmentAppbarListBinding by viewBinding()

    override val viewModel by viewModel<MovieViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val context = view.context

        with(binding) {
            Navigation.findNavController(view).apply {
                AppBarConfiguration(graph).also {
                    binding.toolbar.setupWithNavController(this, it)
                }
            }

            rvMovie.apply {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter.apply { onItemListener = this@MovieFragment }
            }
        }

        with(viewModel) {
            execute(MovieAction.LoadDataMovie)
            viewState.observe(viewLifecycleOwner) {
                it.errorMessage?.let { error -> view.snackbar(error, true) }
                movieAdapter.setDiscoverMovie(it.discoverList)
                movieAdapter.setNowPlayingMovie(it.nowPlayingList)
                binding.loadingMovie.setLoading(it.isLoading)
            }
        }
    }

    private fun intentTo(resId: Int, bundle: Bundle) {
        val context = binding.root.context

        Intent(context, DetailActivity::class.java).apply {
            bundleOf(Const.NAVID to resId, Const.BUNDLE to bundle).also {
                putExtra(Const.DETAIL, it)
            }
            requireActivity().startActivity(this)
        }
    }

    override fun onItemClick(data: Any) {
        when (data) {
            is String -> intentTo(R.id.fragmentListCatalogue, bundleOf(Const.TITLE to data))
            is Int -> intentTo(R.id.fragmentDetailCatalogue, bundleOf(Const.ITEM_ID to data))
        }
    }
}