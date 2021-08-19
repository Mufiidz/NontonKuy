package com.mufidz.nontonkuy.base

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<T : ViewBinding, VM : ViewModel>(layoutId: Int) : Fragment(layoutId) {

    protected abstract val viewModel: VM

    protected abstract val binding: T

}