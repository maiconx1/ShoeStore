package com.udacity.shoestore.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.LoginFragmentBinding
import com.udacity.shoestore.extensions.getStringSafely

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: LoginFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.login_fragment, container, false)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        setListeners()

        setObservers()

        return binding.root
    }

    private fun setListeners() {
        binding.emailEditText.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                viewModel.checkEmail()
            }
        }
        binding.passwordEditText.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                viewModel.checkPassword()
            }
        }
    }

    private fun setObservers() {
        viewModel.emailInputError.observe(viewLifecycleOwner, Observer { error ->
            binding.emailInputLayout.error = getStringSafely(error)
        })
        viewModel.passwordInputError.observe(viewLifecycleOwner, Observer { error ->
            binding.passwordInputLayout.error = getStringSafely(error)
        })
        viewModel.errorMessage.observe(viewLifecycleOwner, Observer { errorMessage ->
            if (errorMessage != null) {
                Snackbar.make(
                    binding.credentialsCard,
                    getStringSafely(errorMessage),
                    Snackbar.LENGTH_SHORT
                ).show()
                viewModel.errorShowed()
            }
        })
        viewModel.performLogin.observe(viewLifecycleOwner, Observer { performLogin ->
            if (performLogin) {
                openShoeList()
                viewModel.finishedLogin()
            }
        })
    }

    private fun openShoeList() {
        //TODO: OPEN SHOE LIST
        Snackbar.make(
            binding.credentialsCard,
            "Open shoe list",
            Snackbar.LENGTH_SHORT
        ).show()
    }
}