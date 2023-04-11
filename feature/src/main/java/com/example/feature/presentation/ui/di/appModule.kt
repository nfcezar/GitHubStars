package com.example.githubstars.viewmodel

import com.example.feature.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel {
        MainViewModel(searchRepository = get(), repoDetailsRepository = get())
    }
}