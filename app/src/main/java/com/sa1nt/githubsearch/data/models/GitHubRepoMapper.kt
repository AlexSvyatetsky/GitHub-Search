package com.sa1nt.githubsearch.data.models

import com.sa1nt.githubsearch.data.remote.GitHubResponse

class GitHubRepoMapper : Mapper<GitHubResponse, GitHubRepoModel> {
    override fun transform(obj: GitHubResponse?): List<GitHubRepoModel> {
        val items = arrayListOf<GitHubRepoModel>()
        if (obj != null && obj.items.isNotEmpty()) {
            for (item in obj.items) {
                items.add(GitHubRepoModel(item.id, item.name, item.html_url, item.stargazersCount))
            }
        }
        return items
    }
}