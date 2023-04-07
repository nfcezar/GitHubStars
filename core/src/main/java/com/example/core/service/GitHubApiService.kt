package com.example.core.service

import retrofit2.http.GET

interface GitHubApiService {
    @GET("search/repositories?q=language:kotlin&sort=stars")
    suspend fun search(): String
}

