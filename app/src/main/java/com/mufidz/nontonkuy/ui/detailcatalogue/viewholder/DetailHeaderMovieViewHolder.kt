package com.mufidz.nontonkuy.ui.detailcatalogue.viewholder

import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.mufidz.nontonkuy.base.BaseViewHolder
import com.mufidz.nontonkuy.databinding.ItemDetailHeaderBinding
import com.mufidz.nontonkuy.entity.MovieEntity
import com.mufidz.nontonkuy.ui.detailcatalogue.DetailCatalogueInfoAdapter

class DetailHeaderMovieViewHolder(private val binding: ItemDetailHeaderBinding) :
    BaseViewHolder<MovieEntity>(binding.root) {

    private val infoAdapter by lazy { DetailCatalogueInfoAdapter() }

    override fun bind(item: MovieEntity) {
        infoAdapter.setMovieData(item)
        with(binding) {
            textTitleCatalogue.apply { text = item.title }
            textGenre.apply { text = item.getListOfGenreNames() }
            listInfo.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = infoAdapter
            }
            imagePosterDetail.apply { load(item.getUrlPosterImage()) }
        }
    }

}
