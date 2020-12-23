package com.udacity.shoestore.shoelist

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
import com.udacity.shoestore.databinding.ItemShoeBinding
import com.udacity.shoestore.databinding.ShoeListFragmentBinding
import com.udacity.shoestore.models.Shoe

class ShoeListFragment : Fragment() {

    private lateinit var viewModel: ShoeViewModel

    private lateinit var binding: ShoeListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.shoe_list_fragment, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(ShoeViewModel::class.java)
        binding.viewModel = viewModel

        setObservers()
        return binding.root
    }

    private fun setObservers() {
        viewModel.shoeListLiveData.observe(viewLifecycleOwner, Observer { shoeList ->
            binding.apply {
                shoeListLayout.removeAllViews()
                for (i in shoeListLayout.childCount until shoeList.size) {
                    val shoe = shoeList[i]
                    val bindingView: ItemShoeBinding = DataBindingUtil.inflate(
                        layoutInflater,
                        R.layout.item_shoe,
                        shoeListLayout,
                        false
                    )
                    bindingView.apply {
                        companyText.text = shoe.company
                        nameText.text = shoe.name
                        sizeText.text = getString(R.string.size_value, shoe.size)
                        if (i == 0) divider.visibility = View.GONE
                        layout.setOnClickListener {
                            openDetails(shoe)
                            this@ShoeListFragment.viewModel.detailsIndex = i
                        }
                    }
                    shoeListLayout.addView(bindingView.root)
                }
            }
        })
        viewModel.openDetails.observe(viewLifecycleOwner, Observer { open ->
            if (open) {
                openDetails(null)
                viewModel.finishOpenDetails()
                viewModel.detailsIndex = -1
            }
        })
        viewModel.shouldSave.observe(viewLifecycleOwner, Observer { shoe ->
            if (shoe != null) {
                viewModel.editShoe(shoe)
                viewModel.finishShouldSave()
            }
        })
    }

    private fun openDetails(shoe: Shoe?) {
        val action = ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailsFragment()
        action.shoe = shoe
        findNavController().navigate(action)
    }
}