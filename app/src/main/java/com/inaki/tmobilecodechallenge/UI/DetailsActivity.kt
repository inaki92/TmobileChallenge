package com.inaki.tmobilecodechallenge.UI

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.inaki.tmobilecodechallenge.R
import com.inaki.tmobilecodechallenge.UI.Fragments.DetailsUserFragment
import com.inaki.tmobilecodechallenge.UI.Fragments.ReposFragment

class DetailsActivity : AppCompatActivity(), DetailsUserFragment.OnFragmentInteractionListener,
            ReposFragment.OnFragmentInteractionListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
    }

    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
