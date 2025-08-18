package com.rff.boingballdemo

import android.app.Application
import com.rff.boingballdemo.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class BoingBallApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@BoingBallApplication)
            modules(appModule)
        }
    }
}
