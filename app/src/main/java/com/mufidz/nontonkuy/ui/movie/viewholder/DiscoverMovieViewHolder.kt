package com.mufidz.nontonkuy.ui.movie.viewholder

import androidx.recyclerview.widget.LinearLayoutManager
import com.mufidz.nontonkuy.base.BaseViewHolder
import com.mufidz.nontonkuy.base.ItemListener
import com.mufidz.nontonkuy.databinding.ItemHomeMovieBinding
import com.mufidz.nontonkuy.entity.MovieEntity
import com.mufidz.nontonkuy.ui.movie.MovieAdapter
import com.mufidz.nontonkuy.utils.visible

class DiscoverMovieViewHolder(
    private val binding: ItemHomeMovieBinding,
    private val listener: ItemListener<Any>?
) : BaseViewHolder<List<MovieEntity>>(binding.root) {

    private val movieAdapter by lazy { MovieAdapter() }

    override fun bind(item: List<MovieEntity>) {
        val title = "Discover"
        with(binding) {
            tvTitleItemHomeMovie.apply {
                visible()
                text = title
            }
            rvItemHomeMovie.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = movieAdapter
                setHasFixedSize(true)
                visible()
            }
            chip.apply {
                visible()
                setOnClickListener { listener?.onItemClick(title) }
            }
        }
        movieAdapter.apply {
            listener?.let { onItemListener = it }
            setData(item)
        }
    }


}
