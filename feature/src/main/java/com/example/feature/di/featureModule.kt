package com.example.feature.di

import com.example.core.api.GitHubApi
import com.example.feature.presentation.viewmodel.MainViewModel
import com.example.feature.repository.GitHubRepository
import com.example.feature.repository.GitHubRepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureModule = module {
    single<GitHubRepository> {
        GitHubRepositoryImpl(
            gitHubApiService = GitHubApi.createApi()
        )
    }
    viewModel {
        MainViewModel(repository = get())
    }
}