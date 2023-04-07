package com.example.feature.repository

import com.example.core.service.GitHubApiService

class GitHubRepository(
    private val gitHubApiService: GitHubApiService
) {
    suspend fun getData() {
        gitHubApiService.search()
    }
}