package com.inaki.tmobilecodechallenge.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.inaki.tmobilecodechallenge.Model.Repositories.RepoModel
import com.inaki.tmobilecodechallenge.R
import com.inaki.tmobilecodechallenge.Util.RecyclerOnclick
import kotlinx.android.synthetic.main.repository_items.view.*

class ReposAdapter(private val context: Context,
                   private val listRepos: List<RepoModel>,
                   private val onClickRecycler: RecyclerOnclick):
RecyclerView.Adapter<ReposAdapter.ReposViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReposViewHolder {
        val reposView = LayoutInflater.from(context).inflate(R.layout.repository_items,parent,false)
        return ReposViewHolder(reposView, onClickRecycler)
    }

    override fun getItemCount(): Int {
        return listRepos.size
    }

    override fun onBindViewHolder(holder: ReposViewHolder, position: Int) {
        holder.forksCount.text = listRepos[position].forks.toString()
        holder.repoName.text = listRepos[position].name
        holder.starsCount.text = listRepos[position].stargazersCount.toString()
    }

    class ReposViewHolder(itemView: View, onClickRecycler: RecyclerOnclick):
        RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private var clickListener: RecyclerOnclick
        var repoName = itemView.repo_name!!
        var forksCount = itemView.forks_count!!
        var starsCount = itemView.stars_count!!

        init {
            itemView.setOnClickListener(this)
            clickListener = onClickRecycler
        }

        override fun onClick(v: View?) {
            clickListener.onClickListener(repoName.text.toString(), adapterPosition)
        }

    }
}