package com.sa1nt.githubsearch.ui

import android.annotation.SuppressLint
import com.sa1nt.githubsearch.data.models.GitHubRepoMapper
import com.sa1nt.githubsearch.data.remote.DataManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executors

class MainPresenter(private val dataManager: DataManager) {

    private var view: MainView? = null

    fun takeView(view: MainView?) {
        this.view = view
    }

    @SuppressLint("CheckResult")
    fun searchGitHubRepo(search: String) {
        dataManager.searchRepo(search)
            .map { response ->
                {
                    GitHubRepoMapper().transform(response.body())
                }
            }
            .subscribeOn(Schedulers.from(Executors.newFixedThreadPool(2)))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                view?.setRepoList(result.invoke())
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