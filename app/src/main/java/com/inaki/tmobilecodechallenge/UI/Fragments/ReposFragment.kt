package com.inaki.tmobilecodechallenge.UI.Fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.inaki.tmobilecodechallenge.Adapters.ReposAdapter
import com.inaki.tmobilecodechallenge.Model.Repositories.RepoModel

import com.inaki.tmobilecodechallenge.R
import com.inaki.tmobilecodechallenge.UI.DetailsActivity
import com.inaki.tmobilecodechallenge.Util.RecyclerOnclick
import com.inaki.tmobilecodechallenge.ViewModel.ReposViewModel
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.rxkotlin.toObservable
import kotlinx.android.synthetic.main.fragment_repos.*
import kotlinx.android.synthetic.main.fragment_repos.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class ReposFragment : Fragment(), RecyclerOnclick {

    private var listener: OnFragmentInteractionListener? = null

    private lateinit var listRepos: List<RepoModel>
    private lateinit var repoByName: List<RepoModel>
    private lateinit var repoAdapter: ReposAdapter
    private lateinit var repoRecycler: RecyclerView
    private var userName: String? = null

    private val reposViewModel: ReposViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val reposView = inflater.inflate(R.layout.fragment_repos, container, false)

        repoRecycler = reposView.repos_recycler
        repoRecycler.setHasFixedSize(true)
        repoRecycler.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)

        reposView.repo_search.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                repoByName = listRepos.filter { repoName -> repoName.name.toLowerCase() == query }
                repoAdapter = ReposAdapter(context!!, repoByName,this@ReposFragment)
                repoRecycler.adapter = repoAdapter
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        reposView.repo_search.setOnCloseListener {
            loadReposFromUser(userName!!)
            repo_search.onActionViewCollapsed()
            true
        }

        return reposView
    }

    private fun loadReposFromUser(user: String){
        reposViewModel.getAllReposFromUser(user).observe(this, Observer { listReposFromUser ->
            listRepos = listReposFromUser
            repoAdapter = ReposAdapter(context!!,listRepos,this)
            repoRecycler.adapter = repoAdapter
        })
    }

    @SuppressLint("CheckResult")
    private fun clickToWebsiteSearched(position: Int){
        val urlRepo = listOf(repoByName[position].htmlUrl)
        urlRepo.toObservable().subscribeBy(
            onNext = {
                val webIntent = Intent(Intent.ACTION_VIEW)
                webIntent.data = Uri.parse(it)
                startActivity(webIntent)
            },
            onError = { Toast.makeText(context,it.message, Toast.LENGTH_SHORT).show() }
        )
    }

    @SuppressLint("CheckResult")
    override fun onClickListener(username: String, position: Int) {
        val urlRepo = listOf(listRepos[position].htmlUrl)
        urlRepo.toObservable().subscribeBy(
            onNext = {
                val webIntent = Intent(Intent.ACTION_VIEW)
                webIntent.data = Uri.parse(it)
                startActivity(webIntent)
            },
            onError = { Toast.makeText(context,it.message, Toast.LENGTH_SHORT).show() }
        )

        clickToWebsiteSearched(position)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        userName = (activity as DetailsActivity).getUserName()
        loadReposFromUser(userName!!)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }
}
