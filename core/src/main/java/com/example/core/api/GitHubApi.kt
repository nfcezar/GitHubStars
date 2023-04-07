package com.example.core.api

import com.example.core.service.GitHubApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object GitHubApi {
    private const val URL = "https://api.github.com/"

    fun createApi(): GitHubApiService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(URL)
            .build()
            .create(GitHubApiService::class.java)
    }
}