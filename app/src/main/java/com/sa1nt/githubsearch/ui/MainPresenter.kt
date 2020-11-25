package com.sa1nt.githubsearch.ui

import android.annotation.SuppressLint
import com.sa1nt.githubsearch.data.models.GitHubRepoMapper
import com.sa1nt.githubsearch.data.models.GitHubRepoModel
import com.sa1nt.githubsearch.data.remote.DataManager
import com.sa1nt.githubsearch.data.remote.GitHubResponse
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import java.util.concurrent.Executors

class MainPresenter(private val dataManager: DataManager) {

    private var view: MainView? = null

    fun takeView(view: MainView?) {
        this.view = view
    }

    @SuppressLint("CheckResult")
    fun searchGitHubRepo(search: String) {
        Single.zip(dataManager.searchRepo(search, 1), dataManager.searchRepo(search, 2),
            BiFunction<Response<GitHubResponse>, Response<GitHubResponse>, List<GitHubRepoModel>> { gitHubResponseResponse: Response<GitHubResponse>,
                                                                                                    gitHubResponseResponse2: Response<GitHubResponse> ->
                val result = arrayListOf<GitHubRepoModel>()
                result.addAll(GitHubRepoMapper().transform(gitHubResponseResponse.body()))
                result.addAll(GitHubRepoMapper().transform(gitHubResponseResponse2.body()))
                return@BiFunction result
            }
        )
            .subscribeOn(Schedulers.from(Executors.newFixedThreadPool(2)))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list ->
                view?.setRepoList(list)
            }, { throwable ->
                view?.showError(throwable.message)
            })
    }

    fun dropView() {
        if (view != null) {
            view = null
        }
    }

}