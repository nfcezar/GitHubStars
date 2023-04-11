package com.example.core.repository

import com.example.core.model.GitHubRepo
import com.example.core.response.SearchResponse
import com.example.core.service.GitHubApiService
import retrofit2.Response

class SearchRepository(
    private val apiService: GitHubApiService
) : BaseRepository {
    override suspend fun fetchRemote(): Response<SearchResponse> =
        apiService.search()
    override suspend fun getDataFromResponse(response: Response<SearchResponse>): List<GitHubRepo> =
        response.body()!!.items
}
