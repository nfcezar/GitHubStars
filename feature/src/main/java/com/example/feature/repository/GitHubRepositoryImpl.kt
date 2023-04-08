package com.example.feature.repository

import com.example.core.model.GitHubItem
import com.example.core.service.GitHubApiService

class GitHubRepositoryImpl(
    private val gitHubApiService: GitHubApiService
) : GitHubRepository {
    override suspend fun getData(): List<GitHubItem> {
        return gitHubApiService.search()
    }
}