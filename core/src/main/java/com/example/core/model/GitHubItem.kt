package com.example.core.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class GitHubItem(
    val id: Int,
    val name: String,
    @SerializedName("stargazers_count")
    val stars: Int,
    @SerializedName("forks_count")
    val forks: Int,
    @SerializedName("avatar_url")
    val photo: String, //URL
    @SerializedName("full_name")
    val repoName: String
)
