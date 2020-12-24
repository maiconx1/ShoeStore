package com.udacity.shoestore.instructions

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
import com.udacity.shoestore.databinding.InstructionsFragmentBinding

class InstructionsFragment : Fragment() {

    private lateinit var viewModel: InstructionsViewModel
    private lateinit var binding: InstructionsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.instructions_fragment, container, false)
        viewModel = ViewModelProvider(this).get(InstructionsViewModel::class.java)
        binding.viewModel = viewModel
        viewModel.openShoeList.observe(viewLifecycleOwner, Observer { open ->
            if (open) {
                viewModel.finishOpen()
                openShoeList()
            }
        })

        return binding.root
    }

    private fun openShoeList() {
        val action = InstructionsFragmentDirections.actionInstructionsFragmentToShoeListFragment()
        findNavController().navigate(action)
    }
}