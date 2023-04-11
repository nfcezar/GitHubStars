package com.example.core.di

import com.example.core.api.GitHubApi
import com.example.core.repository.RepoDetailsRepository
import com.example.core.repository.SearchRepository
import org.koin.dsl.module

val featureModule = module {
    factory {
        SearchRepository(
            GitHubApi.createInstance()
        )
    }

    factory {
        RepoDetailsRepository(
            GitHubApi.createInstance()
        )
    }
}