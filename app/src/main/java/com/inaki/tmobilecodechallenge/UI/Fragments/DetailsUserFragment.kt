package com.inaki.tmobilecodechallenge.UI.Fragments

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.inaki.tmobilecodechallenge.Model.RepoUserModel

import com.inaki.tmobilecodechallenge.R
import com.inaki.tmobilecodechallenge.Util.RecyclerOnclick
import com.inaki.tmobilecodechallenge.ViewModel.ReposViewModel
import kotlinx.android.synthetic.main.fragment_details_user.view.*
import org.koin.android.viewmodel.ext.android.viewModel

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class DetailsUserFragment : Fragment(), RecyclerOnclick {

    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    private lateinit var userDetails: RepoUserModel
    private lateinit var username: String

    private val viewModel: ReposViewModel by viewModel()

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
        val detailView = inflater.inflate(R.layout.fragment_details_user, container, false)

        loadDetailsUser("",detailView)

        return detailView
    }

    private fun loadDetailsUser(userName: String, detailView: View){
        viewModel.getUsersSearched("mojombo").observe(this, Observer { detailsUser ->
            userDetails = detailsUser
            loadUiData(detailView)
        })
    }

    @SuppressLint("SetTextI18n")
    private fun loadUiData(detailView: View){
        detailView.name.text = "Username: "+userDetails.login
        detailView.joinDate.text = "Joined: "+userDetails.createdAt
        detailView.location.text =
            if (userDetails.location.isNullOrEmpty()){
                "Location not available"
            }else{
                "Located at: "+userDetails.location
            }
        detailView.followers.text = "Followers: "+userDetails.followers.toString()
        detailView.following.text = "Following: "+userDetails.following.toString()
        detailView.bio.text =
            if (userDetails.bio == null){
                "Biography not available"
            }else{
                userDetails.bio.toString()
            }
        detailView.email.text =
            if (userDetails.email == null){
                "Email not available"
            }else{
                "Email: "+userDetails.email.toString()
            }
    }

    override fun onClickListener(username: String, position: Int) {
        this.username = username
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
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DetailsUserFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
