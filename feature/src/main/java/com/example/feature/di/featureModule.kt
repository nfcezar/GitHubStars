package com.example.feature.di

import com.example.core.api.GitHubApi
import com.example.feature.presentation.viewmodel.MainViewModel
import com.example.feature.repository.RepoDetailsRepository
import com.example.feature.repository.SearchRepository
import org.koin.androidx.viewmodel.dsl.viewModel
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

    viewModel {
        MainViewModel(searchRepository = get(), repoDetailsRepository = get())
    }
}