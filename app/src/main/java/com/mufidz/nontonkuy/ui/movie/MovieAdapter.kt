package com.mufidz.nontonkuy.ui.movie

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.mufidz.nontonkuy.R
import com.mufidz.nontonkuy.base.BaseAdapter
import com.mufidz.nontonkuy.base.BaseViewHolder
import com.mufidz.nontonkuy.databinding.ItemMovieBinding
import com.mufidz.nontonkuy.entity.MovieEntity
import com.mufidz.nontonkuy.utils.inflate

class MovieAdapter : BaseAdapter<MovieEntity, Any>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ViewHolder(ItemMovieBinding.bind(parent.inflate(R.layout.item_movie)))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? ViewHolder)?.bind(list[position])
    }

    inner class ViewHolder(private val binding: ItemMovieBinding) :
        BaseViewHolder<MovieEntity>(binding.root) {

        override fun bind(item: MovieEntity) {
            with(binding) {
                tvTitleItemMovie.text = item.title
                imgItemMovie.load(item.getUrlPosterImage())
                itemView.setOnClickListener {
                    item.id?.let {
                        onItemListener?.onItemClick(it)
                    }
                }
            }
        }
    }
}