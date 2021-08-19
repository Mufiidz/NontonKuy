package com.mufidz.nontonkuy.ui.detailcatalogue

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mufidz.nontonkuy.R
import com.mufidz.nontonkuy.base.BaseSection
import com.mufidz.nontonkuy.base.ItemListener
import com.mufidz.nontonkuy.databinding.ItemDetailCreditsBinding
import com.mufidz.nontonkuy.databinding.ItemDetailHeaderBinding
import com.mufidz.nontonkuy.databinding.ItemDetailOverviewBinding
import com.mufidz.nontonkuy.entity.MovieEntity
import com.mufidz.nontonkuy.entity.CreditsEntity
import com.mufidz.nontonkuy.ui.detailcatalogue.viewholder.DetailCreditsViewHolder
import com.mufidz.nontonkuy.ui.detailcatalogue.viewholder.DetailHeaderMovieViewHolder
import com.mufidz.nontonkuy.ui.detailcatalogue.viewholder.DetailOverviewViewHolder
import com.mufidz.nontonkuy.ui.detailcatalogue.viewholder.DetailRecommendViewHolder
import com.mufidz.nontonkuy.utils.inflate

class DetailCatalogueAdapter : BaseSection<DetailCatalogueSection>() {

    var onItemListener: ItemListener<Any>? = null

    override val section: MutableList<DetailCatalogueSection> =
        DetailCatalogueSection.getLayoutMovie()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            DetailCatalogueSection.DETAIL_HEADER_MOVIE ->
                DetailHeaderMovieViewHolder(
                    ItemDetailHeaderBinding.bind(parent.inflate(R.layout.item_detail_header))
                )
            DetailCatalogueSection.DETAIL_OVERVIEW ->
                DetailOverviewViewHolder(
                    ItemDetailOverviewBinding.bind(parent.inflate(R.layout.item_detail_overview))
                )
            DetailCatalogueSection.DETAIL_CAST ->
                DetailCreditsViewHolder(
                    ItemDetailCreditsBinding.bind(parent.inflate(R.layout.item_detail_credits)),
                    onItemListener
                )
            DetailCatalogueSection.DETAIL_RECOMMENDATIONS ->
                DetailRecommendViewHolder(
                    ItemDetailCreditsBinding.bind(parent.inflate(R.layout.item_detail_credits)),
                    onItemListener
                )
            else -> throw IllegalArgumentException("Unknown View")
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.apply {
            when (this) {
                is DetailHeaderMovieViewHolder -> {
                    val data = section[position] as DetailHeaderMovie
                    data.movieEntity?.let { bind(it) }
                }
                is DetailOverviewViewHolder -> {
                    val data = section[position] as DetailOverview
                    bind(data.overview.orEmpty())
                }
                is DetailCreditsViewHolder -> {
                    val data = section[position] as DetailCredits
                    data.credits?.let { bind(it) }
                }
                is DetailRecommendViewHolder -> {
                    val data = section[position] as DetailRecommendations
                    data.listRecommend?.let { bind(it) }
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setMovieData(movieEntity: MovieEntity) {
        getSectionPosition(DetailCatalogueSection.DETAIL_HEADER_MOVIE)?.let {
            section[it] = DetailCatalogueSection.getHeaderDetailMovie(movieEntity)
            notifyDataSetChanged()
        }

        getSectionPosition(DetailCatalogueSection.DETAIL_OVERVIEW)?.let {
            section[it] =
                DetailCatalogueSection.getOverviewDetailCatalogue(movieEntity.overview.orEmpty())
            notifyDataSetChanged()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setCreditsMovie(credits: CreditsEntity) {
        getSectionPosition(DetailCatalogueSection.DETAIL_CAST)?.let {
            section[it] = DetailCatalogueSection.getCreditsDetailCatalogue(credits)
            notifyDataSetChanged()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setSimilarMovie(list: List<MovieEntity>) {
        getSectionPosition(DetailCatalogueSection.DETAIL_RECOMMENDATIONS)?.let {
            section[it] = DetailCatalogueSection.getRecommendDetailCatalogue(list)
            notifyDataSetChanged()
        }
    }
}