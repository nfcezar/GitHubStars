package com.example.githubstars

import android.app.Application
import com.example.core.api.GitHubApi

private val api = GitHubApi

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        api.createApi()
    }
}