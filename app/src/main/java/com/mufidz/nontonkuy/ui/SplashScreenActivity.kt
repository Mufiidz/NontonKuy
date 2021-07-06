package com.mufidz.nontonkuy.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import coil.load
import com.mufidz.nontonkuy.BuildConfig
import com.mufidz.nontonkuy.base.BaseActivity
import com.mufidz.nontonkuy.databinding.ActivitySplashscreenBinding
import com.mufidz.nontonkuy.utils.Const

class SplashScreenActivity :
    BaseActivity<ActivitySplashscreenBinding>(ActivitySplashscreenBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(binding) {
            tvVersionName.text = BuildConfig.VERSION_NAME
            imgPowered.load(Const.LOGO_TMDB)
        }

        Handler(mainLooper).postDelayed({
            Intent(this, MainActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(it)
            }
            finish()
        }, 3000)
    }
}