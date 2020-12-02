package com.udacity.shoestore.welcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.udacity.shoestore.R

class WelcomeFragment : Fragment() {

    private lateinit var viewModel: WelcomeViewModel
    

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.welcome_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(WelcomeViewModel::class.java)

        setObservers()
    }

    private fun setObservers() {
        viewModel.next.observe(viewLifecycleOwner, Observer {
            viewModel.finishedNext()
            openInstructions()
        })
        viewModel.skipInstructions.observe(viewLifecycleOwner, Observer {
            viewModel.finishedSkip()
            openShoeList()
        })
    }

    private fun openShoeList() {
        //TODO: OPEN SHOE LIST
    }

    private fun openInstructions() {
        //TODO: OPEN INSTRUCTIONS
    }
}