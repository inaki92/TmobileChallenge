package com.inaki.tmobilecodechallenge.UI.Fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.inaki.tmobilecodechallenge.Adapters.UsersAdapter
import com.inaki.tmobilecodechallenge.Model.Users.RepoUserModel

import com.inaki.tmobilecodechallenge.R
import com.inaki.tmobilecodechallenge.UI.DetailsActivity
import com.inaki.tmobilecodechallenge.Util.RecyclerOnclick
import com.inaki.tmobilecodechallenge.ViewModel.ReposViewModel
import kotlinx.android.synthetic.main.fragment_search_user.*
import kotlinx.android.synthetic.main.fragment_search_user.view.*
import kotlinx.android.synthetic.main.users_items.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class SearchUserFragment : Fragment(), RecyclerOnclick {

    private var listener: OnFragmentInteractionListener? = null

    private lateinit var users: List<RepoUserModel>
    private lateinit var searchedUser: RepoUserModel
    private lateinit var searchedRecycler: RecyclerView
    private lateinit var usersAdapter: UsersAdapter
    private var repoNum = mutableListOf<Int>()

    private val reposViewModel: ReposViewModel by viewModel()

    @SuppressLint("RtlHardcoded")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val searchView = inflater.inflate(R.layout.fragment_search_user, container, false)

        searchedRecycler = searchView.user_recycler
        searchedRecycler.setHasFixedSize(true)
        searchedRecycler.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)

        loadAllUsers()

        searchView.search_bar.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                loadSearchedUser(query!!)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        searchView.search_bar.setOnCloseListener {
            loadAllUsers()
            search_bar.onActionViewCollapsed()
            true
        }

        return searchView
    }

    private fun loadAllUsers(){
        reposViewModel.getAllUsers().observe(this, Observer { listAllUsers ->
            users = listAllUsers
            usersAdapter = UsersAdapter(context!!,users,this)
            searchedRecycler.adapter = usersAdapter
        })
    }

    private fun loadSearchedUser(userName: String){
        reposViewModel.getUsersSearched(userName).observe(this, Observer { userSearched ->
            searchedUser = userSearched
            usersAdapter = UsersAdapter(context!!, listOf(searchedUser),this)
            searchedRecycler.adapter = usersAdapter
        })
    }

    override fun onClickListener(username: String, position: Int) {
        val detailsIntent = Intent(context, DetailsActivity::class.java)
        detailsIntent.putExtra("username", username)
        startActivity(detailsIntent)
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
