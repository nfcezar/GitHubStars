package com.example.githubstars

import android.app.Application
import com.example.core.api.GitHubApi
import com.example.feature.di.featureModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

private lateinit var api: GitHubApi
class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            modules(
                featureModule
            )
        }

        api = GitHubApi
        api.createApi()
    }
}