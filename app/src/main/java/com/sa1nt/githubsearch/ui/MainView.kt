package com.sa1nt.githubsearch.ui

import com.sa1nt.githubsearch.data.models.GitHubRepoModel

interface MainView {
    fun setRepoList(repoList: List<GitHubRepoModel>)
    fun showError(message: String?)
}