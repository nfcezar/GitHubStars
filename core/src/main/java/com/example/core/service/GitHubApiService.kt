package com.example.core.service

import com.example.core.response.OwnerRepoResponse
import com.example.core.response.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val REPO_URL: String = "search/repositories"

interface GitHubApiService {
    @GET(REPO_URL)
    suspend fun search(
        @Query("q") searchByKotlinLanguage: String = "language:kotlin",
        @Query("sort") sortByStars: String = "stars"
    ): Response<SearchResponse>

    @GET("repos/{owner}/{name}")
    suspend fun getRepo(
        @Path("owner") owner: String,
        @Path("name") name: String
    ): Response<OwnerRepoResponse>
}

