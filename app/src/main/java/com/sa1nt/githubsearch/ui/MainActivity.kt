package com.sa1nt.githubsearch.ui

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.sa1nt.githubsearch.R
import com.sa1nt.githubsearch.data.models.GitHubRepoModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity(), MainView {

    @Inject
    internal lateinit var presenter: MainPresenter
    lateinit var adapter: RepoListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.takeView(this)

        adapter = RepoListAdapter()
        rvRepoList.adapter = adapter
        rvRepoList.layoutManager = LinearLayoutManager(this)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val searchView: SearchView = menu?.findItem(R.id.search)?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (!newText.isNullOrEmpty()) {
                    presenter.searchGitHubRepo(newText)
                } else {
                    rvRepoList.visibility = View.GONE
                    tvEmptyList.visibility = View.VISIBLE
                    adapter.clearList()
                }
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun setRepoList(repoList: List<GitHubRepoModel>) {
        rvRepoList.visibility = if (repoList.isNotEmpty()) View.VISIBLE else View.GONE
        tvEmptyList.visibility = if (repoList.isEmpty()) View.VISIBLE else View.GONE
        adapter.setRepoList(repoList)
    }

    override fun showError(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dropView()
    }
}