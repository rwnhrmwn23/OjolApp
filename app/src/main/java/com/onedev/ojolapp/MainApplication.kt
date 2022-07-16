package com.onedev.ojolapp

import android.app.Application
import android.content.Context
import com.onedev.ojolapp.di.MainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        startKoin {
            androidContext(this@MainApplication)
            modules(MainModule().module)
        }
    }

    companion object {
        var appContext: Context? = null
            private set
    }
}
