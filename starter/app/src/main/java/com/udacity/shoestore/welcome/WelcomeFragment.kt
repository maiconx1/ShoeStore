package com.udacity.shoestore.welcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.WelcomeFragmentBinding

class WelcomeFragment : Fragment() {

    private lateinit var viewModel: WelcomeViewModel
    private lateinit var binding: WelcomeFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.welcome_fragment, container, false)
        viewModel = ViewModelProvider(this).get(WelcomeViewModel::class.java)
        binding.viewModel = viewModel
        setObservers()
        return binding.root
    }

    private fun setObservers() {
        viewModel.next.observe(viewLifecycleOwner, Observer { next ->
            if (next) {
                openInstructions()
                viewModel.finishedNext()
            }
        })
        viewModel.skipInstructions.observe(viewLifecycleOwner, Observer { skip ->
            if (skip) {
                viewModel.finishedSkip()
                openShoeList()
            }
        })
    }

    private fun openShoeList() {
        val action = WelcomeFragmentDirections.actionWelcomeFragmentToShoeListFragment()
        findNavController().navigate(action)
    }

    private fun openInstructions() {
        val action = WelcomeFragmentDirections.actionWelcomeFragmentToInstructionsFragment()
        findNavController().navigate(action)
    }
}