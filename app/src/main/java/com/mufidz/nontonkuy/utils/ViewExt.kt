package com.mufidz.nontonkuy.utils

import android.app.Application
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.navigation.NavOptionsBuilder
import coil.Coil
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.util.CoilUtils
import com.google.android.material.snackbar.Snackbar
import com.mufidz.nontonkuy.R
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import java.util.concurrent.TimeUnit

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun ProgressBar.setLoading(isLoading: Boolean) {
    if (isLoading) visible() else gone()
}

fun ViewGroup.inflate(@LayoutRes layoutId: Int): View {
    return LayoutInflater.from(context).inflate(layoutId, this, false)
}

fun String.logD(tag: String? = "data2") {
    return Timber.tag(tag).d(this)
}

fun NavOptionsBuilder.slideLeftRightAnim() {
    anim {
        enter = R.anim.slide_in_right
        exit = R.anim.slide_out_left
        popEnter = R.anim.slide_in_left
        popExit = R.anim.slide_out_right
    }
}

fun View.snackbar(message: CharSequence, isError: Boolean) {
    return Snackbar.make(this, message, Snackbar.LENGTH_LONG)
        .apply {
            if (isError) {
                setBackgroundTint(ContextCompat.getColor(context, R.color.red))
                setTextColor(ContextCompat.getColor(context, R.color.white))
            }
            animationMode = Snackbar.ANIMATION_MODE_SLIDE
        }.show()
}

fun Application.initCoil() {
    val loggerInterceptor = HttpLoggingInterceptor { message ->
        Timber.tag("Image Request").d(message)
    }.also {
        it.redactHeader("Authorization")
        it.redactHeader("Cookies")
        it.level = HttpLoggingInterceptor.Level.BASIC
    }
    val coilOkHttpClient = OkHttpClient.Builder().also {
        it.addInterceptor(loggerInterceptor)
        it.connectTimeout(30, TimeUnit.SECONDS)
        it.readTimeout(30, TimeUnit.SECONDS)
        it.cache(CoilUtils.createDefaultCache(this))
    }.build()

    val imageLoader = ImageLoader.Builder(this).apply {
        crossfade(true)
        crossfade(500)
        error(R.drawable.ic_no_image)
        //placeholder(R.drawable.ic_no_image)
        okHttpClient(coilOkHttpClient)
        componentRegistry {
            add(SvgDecoder(this@initCoil))
        }
    }.build()

    Coil.setImageLoader(imageLoader)
}