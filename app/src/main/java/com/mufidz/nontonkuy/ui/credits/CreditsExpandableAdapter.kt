package com.mufidz.nontonkuy.ui.credits

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.mufidz.nontonkuy.R
import com.mufidz.nontonkuy.databinding.ItemCreditsHeaderBinding
import com.mufidz.nontonkuy.databinding.ItemCreditsHorizontalBinding
import com.mufidz.nontonkuy.entity.ItemCreditsEntity
import com.mufidz.nontonkuy.entity.ItemCreditsGroup
import com.mufidz.nontonkuy.utils.inflate
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

class CreditsExpandableAdapter(private val itemsGroup: ItemCreditsGroup) :
    RecyclerView.Adapter<CreditsExpandableAdapter.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_ITEM = 1
        private const val VIEW_TYPE_HEADER = 2

        private const val IC_EXPANDED_ROTATION_DEG = 0F
        private const val IC_COLLAPSED_ROTATION_DEG = 180F
    }

    private var isExpanded: Boolean by Delegates.observable(true) { _: KProperty<*>, _: Boolean, newExpandedValue: Boolean ->
        if (newExpandedValue) {
            notifyItemRangeInserted(1, itemsGroup.listCredits?.size ?: 0)
            notifyItemChanged(0)
        } else {
            notifyItemRangeRemoved(1, itemsGroup.listCredits?.size ?: 0)
            notifyItemChanged(0)
        }
    }

    private val onHeaderClickListener = View.OnClickListener {
        isExpanded = !isExpanded
    }

    override fun getItemCount() = if (isExpanded) itemsGroup.listCredits?.size?.plus(1) ?: 0 else 1

    override fun getItemViewType(position: Int) =
        if (position == 0) VIEW_TYPE_HEADER else VIEW_TYPE_ITEM

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        when (viewType) {
            VIEW_TYPE_HEADER -> ViewHolder.Header(
                ItemCreditsHeaderBinding.bind(parent.inflate(R.layout.item_credits_header))
            )
            else -> ViewHolder.ItemCredits(
                ItemCreditsHorizontalBinding.bind(parent.inflate(R.layout.item_credits_horizontal))
            )
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            when (this) {
                is ViewHolder.Header -> bind(
                    itemsGroup.title.orEmpty(),
                    isExpanded,
                    onHeaderClickListener
                )
                is ViewHolder.ItemCredits ->
                    itemsGroup.listCredits?.get(position - 1)?.let { bind(it) }
            }
        }
    }

    sealed class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        class Header(private val binding: ItemCreditsHeaderBinding) : ViewHolder(binding.root) {
            fun bind(
                data: String,
                isExpanded: Boolean,
                onHeaderClickListener: View.OnClickListener
            ) {
                with(binding) {
                    root.apply { setOnClickListener(onHeaderClickListener) }
                    tvTitle.apply { text = data }
                    icExpand.apply {
                        rotation =
                            if (isExpanded) IC_EXPANDED_ROTATION_DEG else IC_COLLAPSED_ROTATION_DEG
                        setOnClickListener(onHeaderClickListener)
                    }
                }
            }
        }

        class ItemCredits(private val binding: ItemCreditsHorizontalBinding) :
            ViewHolder(binding.root) {
            fun bind(data: ItemCreditsEntity) {
                with(binding) {
                    imgProfile.load(data.image)
                    tvCreditsName.apply { text = data.name.orEmpty() }
                    tvCreditsDesc.apply { text = data.desc.orEmpty() }
                }
            }
        }
    }
}