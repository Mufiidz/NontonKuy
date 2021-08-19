package com.mufidz.nontonkuy.ui.detailcatalogue.viewholder

import com.mufidz.nontonkuy.R
import com.mufidz.nontonkuy.base.BaseViewHolder
import com.mufidz.nontonkuy.databinding.ItemInfoBaseBinding

class DetailInfoReleaseViewHolder(private val binding: ItemInfoBaseBinding) :
    BaseViewHolder<String>(binding.root) {

    override fun bind(item: String) {
        with(binding) {
            textInfoTitle.apply { text = item }
            textInfoDesc.apply { text = root.context.getString(R.string.release_date) }
        }
    }

}
