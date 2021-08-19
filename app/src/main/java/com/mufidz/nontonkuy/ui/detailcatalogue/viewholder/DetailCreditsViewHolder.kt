package com.mufidz.nontonkuy.ui.detailcatalogue.viewholder

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mufidz.nontonkuy.base.BaseViewHolder
import com.mufidz.nontonkuy.base.ItemListener
import com.mufidz.nontonkuy.databinding.ItemDetailCreditsBinding
import com.mufidz.nontonkuy.entity.CreditsEntity
import com.mufidz.nontonkuy.ui.detailcatalogue.DetailCastAdapter

class DetailCreditsViewHolder(
    private val binding: ItemDetailCreditsBinding,
    private val onItemListener: ItemListener<Any>?
) :
    BaseViewHolder<CreditsEntity>(binding.root) {

    private val castAdapter by lazy { DetailCastAdapter() }

    override fun bind(item: CreditsEntity) {
        castAdapter.apply {
            setListCasts(item.casts ?: emptyList())
            setListCrew(item.crews ?: emptyList())
            setMoreClick {
                onItemListener?.onItemClick(item)
            }
        }
        with(binding) {
            tvTitleCredits.apply { text = "Credits" }
            rvCredits.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(false)
                adapter = castAdapter
                setRecycledViewPool(RecyclerView.RecycledViewPool())
            }
        }
    }
}
