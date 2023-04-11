package com.example.githubstars

import android.app.Application
import com.example.feature.di.featureModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            modules(
                featureModule
            )
        }
    }
}