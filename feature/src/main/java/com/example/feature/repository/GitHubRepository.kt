package com.example.feature.repository

import com.example.core.model.GitHubItem

interface GitHubRepository {
    suspend fun getData(): List<GitHubItem>
}