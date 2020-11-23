package com.sa1nt.githubsearch.data.remote

import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("/search/repositories")
    fun searchRepo(
        @Query(
            "q"
        ) search: String
        , @Query(
            "sort"
        ) sort: String,
        @Query(
            "page"
        ) page: Int,
        @Query(
            "per_page"
        ) perPage: Int
    ): Single<Response<GitHubResponse>>
}