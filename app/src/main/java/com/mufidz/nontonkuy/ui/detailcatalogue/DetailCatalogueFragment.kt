package com.mufidz.nontonkuy.ui.detailcatalogue

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.navOptions
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.google.android.material.appbar.AppBarLayout
import com.mufidz.nontonkuy.R
import com.mufidz.nontonkuy.base.BaseFragment
import com.mufidz.nontonkuy.base.ItemListener
import com.mufidz.nontonkuy.databinding.FragmentDetailBinding
import com.mufidz.nontonkuy.entity.CreditsEntity
import com.mufidz.nontonkuy.utils.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.abs

class DetailCatalogueFragment : BaseFragment<FragmentDetailBinding, DetailCatalogueViewModel>(
    R.layout.fragment_detail
), ItemListener<Any> {

    override val binding: FragmentDetailBinding by viewBinding()

    override val viewModel: DetailCatalogueViewModel by viewModel()

    private val args by navArgs<DetailCatalogueFragmentArgs>()

    private val detailAdapter by lazy { DetailCatalogueAdapter() }

    private val navOptions by lazy { navOptions { slideLeftRightAnim() } }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        with(binding) {
            toolbar.apply {
                title = "Detail Catalogue"
                setNavigationOnClickListener { requireActivity().onBackPressed() }
            }
            initTextViewToolbar(toolbar, appbar, imageTop)
            rvMovie.apply {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = detailAdapter.apply { onItemListener = this@DetailCatalogueFragment }
            }
        }

        viewModel.apply {
            execute(DetailCatalogueAction.LoadDetailMovie(args.itemId))
            viewState.observe(viewLifecycleOwner) {
                it.errorMessage?.let { error -> view.snackbar(error, true) }
                binding.loadingMovie.setLoading(it.isLoading)
                it.movieEntity?.let { movie ->
                    binding.appBarImage.load(movie.getUrlBackDropImage())
                    detailAdapter.setMovieData(movie)
                }
                it.credits?.let { credits -> detailAdapter.setCreditsMovie(credits) }
                it.listRecommend?.let { similar -> detailAdapter.setSimilarMovie(similar) }
            }
        }
    }

    private fun initTextViewToolbar(
        toolbar: Toolbar,
        appBarLayout: AppBarLayout,
        imageView: ImageView
    ) {
        toolbar.alpha = 0f
        appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
            val alpha =
                abs(verticalOffset).toFloat() / (appBarLayout.measuredHeight - toolbar.measuredHeight)
            if (alpha <= 0.5f) {
                toolbar.alpha = alpha
                imageView.visible()
            } else {
                toolbar.alpha = 1.0f
                imageView.gone()
            }
        })
    }

    override fun onItemClick(data: Any) {
        when (data) {
            is Int -> {
                bundleOf(Const.ITEM_ID to data).also {
                    findNavController().navigate(R.id.fragmentDetailCatalogue, it, navOptions)
                }
            }
            is CreditsEntity -> {
                bundleOf(Const.CREDITS to data).also {
                    findNavController().navigate(R.id.dialogCreditsList, it, navOptions)
                }
            }
        }
    }
}