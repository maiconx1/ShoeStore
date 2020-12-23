package com.udacity.shoestore.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class LoginViewModelFactory : ViewModelProvider.Factory {

    private lateinit var loginViewModel: LoginViewModel

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            if (!this::loginViewModel.isInitialized) {
                loginViewModel = LoginViewModel()
            }
            return loginViewModel as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}