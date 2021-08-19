package com.mufidz.nontonkuy.ui.detailcatalogue

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mufidz.nontonkuy.R
import com.mufidz.nontonkuy.base.BaseSection
import com.mufidz.nontonkuy.databinding.ItemInfoBaseBinding
import com.mufidz.nontonkuy.databinding.ItemInfoRatingBinding
import com.mufidz.nontonkuy.entity.MovieEntity
import com.mufidz.nontonkuy.ui.detailcatalogue.viewholder.DetailInfoRatingViewHolder
import com.mufidz.nontonkuy.ui.detailcatalogue.viewholder.DetailInfoReleaseViewHolder
import com.mufidz.nontonkuy.ui.detailcatalogue.viewholder.DetailInfoRuntimeViewHolder
import com.mufidz.nontonkuy.utils.inflate

class DetailCatalogueInfoAdapter : BaseSection<DetailInfoSection>() {

    override val section: MutableList<DetailInfoSection> = DetailInfoSection.getLayoutInfoMovie()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when(viewType) {
            DetailInfoSection.INFO_RATING_MOVIE ->
                DetailInfoRatingViewHolder(
                    ItemInfoRatingBinding.bind(parent.inflate(R.layout.item_info_rating))
                )
            DetailInfoSection.INFO_RELEASE_MOVIE -> DetailInfoReleaseViewHolder(
                ItemInfoBaseBinding.bind(parent.inflate(R.layout.item_info_base))
            )
            DetailInfoSection.INFO_RUNTIME_MOVIE -> DetailInfoRuntimeViewHolder(
                ItemInfoBaseBinding.bind(parent.inflate(R.layout.item_info_base))
            )
            else -> throw IllegalArgumentException("Unknown View")
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.apply {
            when(this) {
                is DetailInfoRatingViewHolder -> {
                    val data = section[position] as InfoRatingMovie
                    bind(data)
                }

                is DetailInfoReleaseViewHolder -> {
                    val data = section[position] as InfoReleaseMovie
                    bind(data.release.orEmpty())
                }

                is DetailInfoRuntimeViewHolder -> {
                    val data = section[position] as InfoRuntimeMovie
                    bind(data.runtime ?: "-")
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setMovieData(movieEntity: MovieEntity) {
        val rating = movieEntity.rating
        val voteCount = movieEntity.voteCount

        getSectionPosition(DetailInfoSection.INFO_RATING_MOVIE)?.let {
            section[it] = DetailInfoSection.getInfoRating(rating, voteCount)
            notifyDataSetChanged()
        }

        getSectionPosition(DetailInfoSection.INFO_RELEASE_MOVIE)?.let {
            section[it] = DetailInfoSection.getInfoRelease(movieEntity.getReleaseDateText())
            notifyDataSetChanged()
        }

        getSectionPosition(DetailInfoSection.INFO_RUNTIME_MOVIE)?.let {
            section[it] = DetailInfoSection.getInfoRuntime(movieEntity.getDurationText())
            notifyDataSetChanged()
        }
    }
}