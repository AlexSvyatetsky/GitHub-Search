package com.sa1nt.githubsearch.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sa1nt.githubsearch.R
import com.sa1nt.githubsearch.data.models.GitHubRepoModel

class RepoListAdapter :
    RecyclerView.Adapter<RepoListAdapter.RepoListViewHolder>() {

    private var repoList: ArrayList<GitHubRepoModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoListViewHolder {
        return RepoListViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.repo_item,
                    parent,
                    false
                )
        )
    }

    override fun onBindViewHolder(holderWallpaperListList: RepoListViewHolder, position: Int) {
        holderWallpaperListList.tvRepoName.text =
            holderWallpaperListList.tvRepoName.context.getString(
                R.string.repo_name,
                repoList[position].name
            )
        holderWallpaperListList.tvStarts.text = holderWallpaperListList.tvStarts.context.getString(
            R.string.stars,
            repoList[position].stargazersCount.toString()
        )
    }

    fun setRepoList(repoList: List<GitHubRepoModel>) {
        this.repoList.clear()
        this.repoList.addAll(repoList)
        notifyDataSetChanged()
    }

    fun clearList() {
        this.repoList.clear()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = repoList.size

    class RepoListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvRepoName: TextView = itemView.findViewById(R.id.tvRepoName)
        val tvStarts: TextView = itemView.findViewById(R.id.tvStarts)

    }
}