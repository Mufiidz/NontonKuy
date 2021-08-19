package com.mufidz.nontonkuy.ui.credits

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mufidz.nontonkuy.base.BaseBottomSheetDialog
import com.mufidz.nontonkuy.databinding.FragmentCreditsListBinding
import com.mufidz.nontonkuy.utils.creditsTransform
import com.mufidz.nontonkuy.utils.viewBinding

class CreditsListFragment : BaseBottomSheetDialog<FragmentCreditsListBinding>() {

    private val args by navArgs<CreditsListFragmentArgs>()

    override val binding: FragmentCreditsListBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemsGroupList = args.credits?.creditsTransform()

        val adapters = itemsGroupList?.map { CreditsExpandableAdapter(it) }

        val concatAdapterConfig = ConcatAdapter.Config.Builder().setIsolateViewTypes(false).build()

        with(binding) {
            rvCredits.apply {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = adapters?.let { ConcatAdapter(concatAdapterConfig, it) }
                addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            }
        }

    }
}