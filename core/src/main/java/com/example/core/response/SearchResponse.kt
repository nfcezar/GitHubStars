package com.example.core.response

import com.example.core.model.GitHubRepo
import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("total_count") val totalCount: Int,
    val items: List<GitHubRepo>
) : BaseResponse