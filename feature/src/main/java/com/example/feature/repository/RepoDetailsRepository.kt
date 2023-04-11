package com.example.feature.repository

import com.example.core.model.GitHubUser
import com.example.core.response.OwnerRepoResponse
import com.example.core.service.GitHubApiService
import retrofit2.Response

class RepoDetailsRepository(
    private val apiService: GitHubApiService
) : BaseDetailsRepository {

    override suspend fun fetchRemote(
        ownerLogin: String,
        repoName: String
    ): Response<OwnerRepoResponse> {
        return apiService.getRepo(ownerLogin, repoName)
    }

    override suspend fun getDataFromResponse(response: Response<OwnerRepoResponse>): List<GitHubUser> {
        return response.body()!!.items
    }
}