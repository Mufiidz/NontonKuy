package com.mufidz.nontonkuy

import android.app.Application
import com.mufidz.nontonkuy.di.homeModules
import com.mufidz.nontonkuy.utils.initCoil
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level
import timber.log.Timber
import timber.log.Timber.DebugTree

class NontonKuy : Application() {

    override fun onCreate() {
        super.onCreate()
        initCoil()
        if (BuildConfig.DEBUG) Timber.plant(DebugTree())
        startKoin {
            androidContext(this@NontonKuy)
            androidLogger(Level.ERROR)
            modules(homeModules)
        }
    }
}