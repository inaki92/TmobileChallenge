package com.inaki.tmobilecodechallenge.UI

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.inaki.tmobilecodechallenge.R
import com.inaki.tmobilecodechallenge.UI.Fragments.DetailsUserFragment
import com.inaki.tmobilecodechallenge.UI.Fragments.ReposFragment

class DetailsActivity : AppCompatActivity(), DetailsUserFragment.OnFragmentInteractionListener,
            ReposFragment.OnFragmentInteractionListener{

    private lateinit var username: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        username = intent.getStringExtra("username")
    }

    fun getUserName(): String{
        return username
    }

    override fun onFragmentInteraction(uri: Uri) {
    }
}
