package com.mufidz.nontonkuy.ui.detailcatalogue.viewholder

import android.annotation.SuppressLint
import coil.load
import com.mufidz.nontonkuy.base.BaseViewHolder
import com.mufidz.nontonkuy.databinding.ItemCreditsBinding
import com.mufidz.nontonkuy.entity.CastEntity

class CreditsPersonViewHolder(private val binding: ItemCreditsBinding) :
    BaseViewHolder<CastEntity>(binding.root) {

    @SuppressLint("SetTextI18n")
    override fun bind(item: CastEntity) {
        with(binding) {
            imgProfile.apply {
                load(item.getFullPathProfileUrl())
            }
            tvCreditsName.apply { text = item.name }
            tvCreditsDesc.apply { text = "as ${item.characterName}" }
        }
    }
}
