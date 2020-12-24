package com.udacity.shoestore.shoedetails

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
import com.udacity.shoestore.databinding.ShoeDetailsFragmentBinding
import com.udacity.shoestore.extensions.getStringSafely
import com.udacity.shoestore.shoelist.ShoeViewModel

class ShoeDetailsFragment : Fragment() {

    private lateinit var viewModel: ShoeViewModel
    private lateinit var binding: ShoeDetailsFragmentBinding
    private lateinit var args: ShoeDetailsFragmentArgs

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.shoe_details_fragment, container, false)

        viewModel = ViewModelProvider(requireActivity()).get(ShoeViewModel::class.java)
        args = ShoeDetailsFragmentArgs.fromBundle(requireArguments())

        binding.viewModel = viewModel

        viewModel.setValues(args.shoe)

        setObservers()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.companyEditText.requestFocus()
    }

    private fun setObservers() {
        viewModel.shoe.observe(viewLifecycleOwner, Observer { shoe ->
            viewModel.setValues(shoe)
        })
        viewModel.shouldSave.observe(viewLifecycleOwner, Observer { shoe ->
            if (shoe != null) {
                findNavController().navigateUp()
            }
        })
        viewModel.nameInputError.observe(viewLifecycleOwner, Observer { error ->
            binding.nameInputLayout.error = getStringSafely(error)
        })
        viewModel.companyInputError.observe(viewLifecycleOwner, Observer { error ->
            binding.companyInputLayout.error = getStringSafely(error)
        })
        viewModel.sizeInputError.observe(viewLifecycleOwner, Observer { error ->
            binding.sizeInputLayout.error = getStringSafely(error)
        })
    }
}