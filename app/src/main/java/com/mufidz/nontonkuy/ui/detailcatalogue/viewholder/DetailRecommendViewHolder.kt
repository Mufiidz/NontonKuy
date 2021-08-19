package com.mufidz.nontonkuy.ui.detailcatalogue.viewholder

import androidx.recyclerview.widget.LinearLayoutManager
import com.mufidz.nontonkuy.R
import com.mufidz.nontonkuy.base.BaseViewHolder
import com.mufidz.nontonkuy.base.ItemListener
import com.mufidz.nontonkuy.databinding.ItemDetailCreditsBinding
import com.mufidz.nontonkuy.entity.MovieEntity
import com.mufidz.nontonkuy.ui.movie.MovieAdapter

class DetailRecommendViewHolder(
    private val binding: ItemDetailCreditsBinding,
    private val listener: ItemListener<Any>?
) : BaseViewHolder<List<MovieEntity>>(binding.root) {

    private val recommendAdapter by lazy { MovieAdapter() }

    override fun bind(item: List<MovieEntity>) {
        with(binding) {
            tvTitleCredits.apply { text = context.getString(R.string.recommendations) }
            rvCredits.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(false)
                adapter = recommendAdapter
            }
        }

        recommendAdapter.apply {
            setData(item)
            listener?.let { onItemListener = it }
        }
    }

}
