package com.sa1nt.githubsearch.data.remote

import com.google.gson.annotations.SerializedName

class GitHubResponse {
    @SerializedName("items")
    val items: List<Items> = listOf()
}

data class Items(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("html_url") val html_url: String,
    @SerializedName("stargazers_count") val stargazersCount: Int
)