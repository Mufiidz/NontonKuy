package com.mufidz.nontonkuy.ui.detailcatalogue.viewholder

import android.annotation.SuppressLint
import android.view.View
import com.mufidz.nontonkuy.base.BaseViewHolder
import com.mufidz.nontonkuy.databinding.ItemCreditsMoreBinding

class CreditsMoreViewHolder(
    private val binding: ItemCreditsMoreBinding,
    private val listener: (View) -> Unit
) : BaseViewHolder<Int>(binding.root) {

    @SuppressLint("SetTextI18n")
    override fun bind(item: Int) {
        itemView.setOnClickListener { listener.invoke(it) }
        with(binding) {
            tvLeftCount.apply { text = "+$item" }
        }
    }

}
