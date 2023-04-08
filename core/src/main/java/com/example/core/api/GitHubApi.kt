package com.example.core.api

import com.example.core.service.GitHubApiService
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

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