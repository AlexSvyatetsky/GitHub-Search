package com.sa1nt.githubsearch.data.remote

import com.sa1nt.githubsearch.utils.PAGE
import com.sa1nt.githubsearch.utils.PER_PAGE
import com.sa1nt.githubsearch.utils.SORT
import io.reactivex.Single
import retrofit2.Response

class DataManager(
    private val api: Api
) {

    fun searchRepo(
        search: String
    ): Single<Response<GitHubResponse>> {
        return api.searchRepo(search, SORT, PAGE, PER_PAGE)
    }


}