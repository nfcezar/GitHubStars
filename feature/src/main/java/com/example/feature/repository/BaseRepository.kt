package com.example.feature.repository

import com.example.core.model.GitHubRepo
import com.example.core.response.SearchResponse
import retrofit2.Response

interface BaseRepository {

    suspend fun fetchRemote(): Response<SearchResponse>

    suspend fun getDataFromResponse(response: Response<SearchResponse>): List<GitHubRepo>
}