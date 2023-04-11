package com.example.githubstars

import android.app.Application
import com.example.core.api.GitHubApi
import com.example.core.di.featureModule
import com.example.githubstars.viewmodel.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

private val api = GitHubApi
class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        api.createInstance()

        startKoin {
            androidContext(this@MainApplication)
            modules(
                featureModule,
                appModule
            )
        }
    }
}