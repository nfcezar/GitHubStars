package com.example.core.service

import com.example.core.model.GitHubItem
import retrofit2.http.GET

interface GitHubApiService {
    @GET("search/repositories?q=language:kotlin&sort=stars")
    suspend fun search(): List<GitHubItem>
}

