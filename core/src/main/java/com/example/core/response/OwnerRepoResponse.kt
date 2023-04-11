package com.example.core.response

import com.example.core.model.GitHubUser
import com.google.gson.annotations.SerializedName

data class OwnerRepoResponse(
    @SerializedName("total_count")
    val total: Int,
    @SerializedName("items")
    val items: List<GitHubUser>
) : BaseResponse
