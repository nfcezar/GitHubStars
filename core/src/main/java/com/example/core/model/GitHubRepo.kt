package com.example.core.model

import com.google.gson.annotations.SerializedName

data class GitHubRepo(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("stargazers_count") val stars: Int,
    @SerializedName("forks") val forks: Int,
    val owner: GitHubUser
)
