package com.inaki.tmobilecodechallenge.UI.Fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.inaki.tmobilecodechallenge.Adapters.UsersAdapter
import com.inaki.tmobilecodechallenge.Model.RepoUserModel

import com.inaki.tmobilecodechallenge.R
import com.inaki.tmobilecodechallenge.Util.RecyclerOnclick
import com.inaki.tmobilecodechallenge.ViewModel.ReposViewModel
import kotlinx.android.synthetic.main.fragment_search_user.view.*
import org.koin.android.viewmodel.ext.android.viewModel

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SearchUserFragment : Fragment(), RecyclerOnclick {

    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    private lateinit var users: List<RepoUserModel>
    private lateinit var searchedUser: RepoUserModel
    private lateinit var searchedRecycler: RecyclerView
    private lateinit var usersAdapter: UsersAdapter

    private val reposViewModel: ReposViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

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
                val userName = query!!
                loadSearchedUser(userName)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

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

    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onClickListener(username: String, position: Int) {
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

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchUserFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
