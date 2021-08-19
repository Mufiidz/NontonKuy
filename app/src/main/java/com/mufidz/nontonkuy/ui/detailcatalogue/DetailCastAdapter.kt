package com.mufidz.nontonkuy.ui.detailcatalogue

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mufidz.nontonkuy.R
import com.mufidz.nontonkuy.databinding.ItemCreditsBinding
import com.mufidz.nontonkuy.databinding.ItemCreditsMoreBinding
import com.mufidz.nontonkuy.entity.CastEntity
import com.mufidz.nontonkuy.entity.CrewEntity
import com.mufidz.nontonkuy.ui.detailcatalogue.viewholder.CreditsMoreViewHolder
import com.mufidz.nontonkuy.ui.detailcatalogue.viewholder.CreditsPersonViewHolder
import com.mufidz.nontonkuy.utils.inflate

class DetailCastAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var listCasts = emptyList<CastEntity>()

    private var listCrew = emptyList<CrewEntity>()

    private lateinit var listener: (View) -> Unit

    companion object {
        private const val PERSON_VIEW_TYPE = 0
        private const val SEE_MORE_VIEW_TYPE = 1
        private const val MAX_PERSON_SHOWN = 8
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == SEE_MORE_VIEW_TYPE) {
            CreditsMoreViewHolder(
                ItemCreditsMoreBinding.bind(parent.inflate(R.layout.item_credits_more)), listener
            )
        } else {
            CreditsPersonViewHolder(
                ItemCreditsBinding.bind(parent.inflate(R.layout.item_credits))
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CreditsPersonViewHolder) {
            val selectedData = listCasts[position]
            holder.bind(selectedData)
        } else if (holder is CreditsMoreViewHolder) {
            (listCasts.size - MAX_PERSON_SHOWN) + listCrew.size.also {
                holder.bind(it)
            }
        }
    }

    override fun getItemCount(): Int =
        if (listCasts.size > MAX_PERSON_SHOWN) MAX_PERSON_SHOWN.plus(1) else listCasts.size

    override fun getItemViewType(position: Int): Int {
        val isHadSeeMoreView = listCasts.size > MAX_PERSON_SHOWN
        if (isHadSeeMoreView) {
            return if (position == MAX_PERSON_SHOWN) {
                SEE_MORE_VIEW_TYPE
            } else {
                PERSON_VIEW_TYPE
            }
        }
        return PERSON_VIEW_TYPE
    }

    fun setListCasts(casts: List<CastEntity>) {
        listCasts = casts
    }

    fun setListCrew(crews: List<CrewEntity>) {
        listCrew = crews
    }

    fun setMoreClick(function: (View) -> Unit) {
        listener = function
    }
}
