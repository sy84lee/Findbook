package com.sy.searchbook

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TheApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(androidContext = this@TheApplication)
            modules(AppModules.modules)
        }
    }

}