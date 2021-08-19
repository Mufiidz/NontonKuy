package com.mufidz.nontonkuy.ui

import android.os.Bundle
import androidx.navigation.findNavController
import com.mufidz.nontonkuy.R
import com.mufidz.nontonkuy.base.BaseActivity
import com.mufidz.nontonkuy.databinding.ActivityDetailBinding
import com.mufidz.nontonkuy.utils.Const

class DetailActivity : BaseActivity<ActivityDetailBinding>(ActivityDetailBinding::inflate) {

    private val navController by lazy { findNavController(R.id.detail_navhost_fragment) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intent.getBundleExtra(Const.DETAIL).also {
            val navId = it?.getInt(Const.NAVID) ?: R.id.movie
            val bundle = it?.getBundle(Const.BUNDLE)

            navController.navInflater.inflate(R.navigation.nav_graph).apply {
                startDestination = navId
                navController.setGraph(this, bundle)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}