package com.example.feature.presentation.ui

import com.example.core.model.GitHubItem

sealed interface GitHubUiState {

    data class Success(
        val items: List<GitHubItem>
    ) : GitHubUiState

    object Error : GitHubUiState
}