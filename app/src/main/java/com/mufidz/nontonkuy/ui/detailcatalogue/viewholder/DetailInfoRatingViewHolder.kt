package com.mufidz.nontonkuy.ui.detailcatalogue.viewholder

import android.annotation.SuppressLint
import com.mufidz.nontonkuy.R
import com.mufidz.nontonkuy.base.BaseViewHolder
import com.mufidz.nontonkuy.databinding.ItemInfoRatingBinding
import com.mufidz.nontonkuy.ui.detailcatalogue.InfoRatingMovie

class DetailInfoRatingViewHolder(private val binding: ItemInfoRatingBinding) :
    BaseViewHolder<InfoRatingMovie>(binding.root) {

    @SuppressLint("SetTextI18n")
    override fun bind(item: InfoRatingMovie) {
        with(binding) {
            val txtWith = root.context.getString(R.string.txt_with)
            val txtVote = root.context.getString(R.string.txt_vote)
            val voteCount = (item.voteCount).toString()

            textRating.apply { text = "${item.rating}" }
            textRatingCount.apply { text = "$txtWith $voteCount $txtVote" }
        }
    }

}
