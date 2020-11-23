package com.sa1nt.githubsearch.data.models

data class GitHubRepoModel constructor(
    var id: Int,
    var name: String,
    var html_url: String,
    var stargazersCount: Int
)