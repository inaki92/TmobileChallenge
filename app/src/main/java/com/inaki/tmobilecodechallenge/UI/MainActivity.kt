package com.inaki.tmobilecodechallenge.UI

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.inaki.tmobilecodechallenge.R
import com.inaki.tmobilecodechallenge.UI.Fragments.SearchUserFragment
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), SearchUserFragment.OnFragmentInteractionListener {

    private val searchFragment: SearchUserFragment by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startFragment(searchFragment)
    }

    private fun startFragment(fragment: Fragment){
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }

    override fun onFragmentInteraction(uri: Uri) {
    }
}
