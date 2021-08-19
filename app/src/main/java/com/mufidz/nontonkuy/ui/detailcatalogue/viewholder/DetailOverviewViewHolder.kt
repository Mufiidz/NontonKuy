package com.mufidz.nontonkuy.ui.detailcatalogue.viewholder

import android.annotation.SuppressLint
import android.os.Build
import android.text.Layout
import androidx.core.view.isVisible
import com.mufidz.nontonkuy.R
import com.mufidz.nontonkuy.base.BaseViewHolder
import com.mufidz.nontonkuy.databinding.ItemDetailOverviewBinding

class DetailOverviewViewHolder(private val binding: ItemDetailOverviewBinding) :
    BaseViewHolder<String>(binding.root) {

    private var isShowMore = true

    @SuppressLint("WrongConstant")
    override fun bind(item: String) {
        with(binding) {
            textOverview.apply {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    justificationMode = Layout.JUSTIFICATION_MODE_INTER_WORD
                }
                text = item
            }
            btnShowMore.apply {
                isVisible = binding.textOverview.lineCount > 3
                setOnClickListener {
                    if (isShowMore) {
                        isShowMore = false
                        text = context.getString(R.string.show_less)
                        binding.textOverview.maxLines = Integer.MAX_VALUE
                    } else {
                        isShowMore = true
                        text = context.getString(R.string.show_more)
                        binding.textOverview.maxLines = 3
                    }
                }
            }
        }
    }

}
