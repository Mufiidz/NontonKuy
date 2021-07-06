package com.mufidz.nontonkuy.base

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.mufidz.nontonkuy.utils.viewBinding

abstract class BaseFragment<T : ViewBinding, VM: ViewModel>(
    layoutId: Int,
    viewBindingFactory: (View) -> T
) : Fragment(layoutId) {

    protected abstract val viewModel: VM

    protected val binding by viewBinding(viewBindingFactory)

}