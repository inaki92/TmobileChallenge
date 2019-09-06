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
import com.inaki.tmobilecodechallenge.Model.Users.RepoUserModel
import com.inaki.tmobilecodechallenge.R
import com.inaki.tmobilecodechallenge.UI.DetailsActivity
import com.inaki.tmobilecodechallenge.Util.dateFormat
import com.inaki.tmobilecodechallenge.ViewModel.ReposViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_details_user.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.viewmodel.ext.android.viewModel

class DetailsUserFragment : Fragment() {

    private var listener: OnFragmentInteractionListener? = null

    private lateinit var userDetails: RepoUserModel
    private var userName: String? = null

    private val viewModel: ReposViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details_user, container, false)
    }

    private fun loadDetailsUser(userName: String, detailView: View){
        viewModel.getUsersSearched(userName).observe(this, Observer { detailsUser ->
            userDetails = detailsUser
            loadUiData(detailView)
        })
    }

    @SuppressLint("SetTextI18n")
    private fun loadUiData(detailView: View){
        detailView.name.text = "Username: "+userDetails.login
        detailView.joinDate.text = "Joined: " + dateFormat(userDetails.createdAt)
        detailView.location.text =
            if (userDetails.location.isNullOrEmpty()){
                resources.getString(R.string.no_location)
            }else{
                "Located at: "+userDetails.location
            }
        detailView.followers.text = "Followers: "+userDetails.followers.toString()
        detailView.following.text = "Following: "+userDetails.following.toString()
        detailView.bio.text =
            if (userDetails.bio == null){
                resources.getString(R.string.no_bio)
            }else{
                userDetails.bio.toString()
            }
        detailView.email.text =
            if (userDetails.email == null){
                resources.getString(R.string.no_email)
            }else{
                "Email: "+userDetails.email.toString()
            }

        loadAvatars(detailView)

    }

    private fun loadAvatars(view: View){
        CoroutineScope(Dispatchers.Main).launch {
            val picture = withContext(Dispatchers.IO) {
                Picasso.get()
                    .load(userDetails.avatarUrl)
                    .resize(50, 50)
                    .centerCrop()
            }
            picture.into(view.detailAvatar)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        userName = (activity as DetailsActivity).getUserName()
        loadDetailsUser(userName!!,view!!.rootView)
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
