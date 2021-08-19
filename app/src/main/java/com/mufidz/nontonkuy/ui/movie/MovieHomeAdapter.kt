package com.mufidz.nontonkuy.ui.movie

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mufidz.nontonkuy.R
import com.mufidz.nontonkuy.base.BaseSection
import com.mufidz.nontonkuy.base.ItemListener
import com.mufidz.nontonkuy.databinding.ItemHomeMovieBinding
import com.mufidz.nontonkuy.entity.MovieEntity
import com.mufidz.nontonkuy.ui.movie.viewholder.DiscoverMovieViewHolder
import com.mufidz.nontonkuy.ui.movie.viewholder.FavoriteMovieViewHolder
import com.mufidz.nontonkuy.ui.movie.viewholder.NowPlayingMovieViewHolder
import com.mufidz.nontonkuy.utils.inflate

class MovieHomeAdapter : BaseSection<MovieSection>() {

    var onItemListener: ItemListener<Any>? = null

    override val section: MutableList<MovieSection> = MovieSection.getLayout()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            MovieSection.NOW_PLAYING_MOVIE ->
                NowPlayingMovieViewHolder(
                    ItemHomeMovieBinding.bind(parent.inflate(R.layout.item_home_movie)),
                    onItemListener
                )
            MovieSection.DISCOVER_MOVIE ->
                DiscoverMovieViewHolder(
                    ItemHomeMovieBinding.bind(parent.inflate(R.layout.item_home_movie)),
                    onItemListener
                )
            MovieSection.FAVORITE_MOVIE ->
                FavoriteMovieViewHolder(
                    ItemHomeMovieBinding.bind(parent.inflate(R.layout.item_home_movie))
                )
            else -> throw IllegalArgumentException("Unknown View")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.apply {
            when (this) {
                is DiscoverMovieViewHolder -> {
                    val data = section[position] as DiscoverMovie
                    data.discoverList?.let { bind(it) }
                }

                is NowPlayingMovieViewHolder -> {
                    val data = section[position] as NowPlayingMovie
                    data.nowPlayingList?.let { bind(it) }
                }

                is FavoriteMovieViewHolder -> { }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setDiscoverMovie(list: List<MovieEntity>) {
        getSectionPosition(MovieSection.DISCOVER_MOVIE)?.let {
            section[it] = MovieSection.getDiscoverMovie(list)
            notifyDataSetChanged()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNowPlayingMovie(list: List<MovieEntity>) {
        getSectionPosition(MovieSection.NOW_PLAYING_MOVIE)?.let {
            section[it] = MovieSection.getNowPlaying(list)
            notifyDataSetChanged()
        }
    }
}
