package com.example.feature.repository

import com.example.core.model.GitHubUser
import com.example.core.response.OwnerRepoResponse
import retrofit2.Response

interface BaseDetailsRepository {

    suspend fun fetchRemote(ownerLogin: String, repoName: String): Response<OwnerRepoResponse>

    suspend fun getDataFromResponse(response: Response<OwnerRepoResponse>): List<GitHubUser>
}