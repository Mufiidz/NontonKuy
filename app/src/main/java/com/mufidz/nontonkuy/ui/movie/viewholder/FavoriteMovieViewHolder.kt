package com.mufidz.nontonkuy.ui.movie.viewholder

import com.mufidz.nontonkuy.base.BaseViewHolder
import com.mufidz.nontonkuy.databinding.ItemHomeMovieBinding
import com.mufidz.nontonkuy.utils.visible

class FavoriteMovieViewHolder(private val binding: ItemHomeMovieBinding) :
    BaseViewHolder<String>(binding.root) {

    override fun bind(item: String) {
        with(binding) {
            tvTitleItemHomeMovie.apply {
                visible()
                text = "Favorite"
            }
            chip.apply {
                visible()
            }
        }
    }

}
