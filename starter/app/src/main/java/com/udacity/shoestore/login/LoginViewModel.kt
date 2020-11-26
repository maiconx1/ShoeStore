package com.udacity.shoestore.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.R
import com.udacity.shoestore.extensions.checkEmail
import com.udacity.shoestore.extensions.checkPassword
import com.udacity.shoestore.models.User
import com.udacity.shoestore.util.Constants

class LoginViewModel : ViewModel() {

    private val userList = mutableListOf<User>()

    var email = MutableLiveData<String>()
    var password = MutableLiveData<String>()

    private val _emailInputError = MutableLiveData<Int>()
    val emailInputError: LiveData<Int>
        get() = _emailInputError

    private val _passwordInputError = MutableLiveData<Int>()
    val passwordInputError: LiveData<Int>
        get() = _passwordInputError

    private val _performLogin = MutableLiveData<Boolean>()
    val performLogin: LiveData<Boolean>
        get() = _performLogin

    private val _errorMessage = MutableLiveData<Int>()
    val errorMessage: LiveData<Int>
        get() = _errorMessage

    fun checkEmail(): Boolean {
        return when (email.value?.checkEmail()) {
            Constants.EMAIL_EMPTY -> {
                _emailInputError.value = R.string.err_email_empty
                false
            }
            Constants.EMAIL_INVALID -> {
                _emailInputError.value = R.string.err_email_invalid
                false
            }
            Constants.FIELD_VALID -> {
                _emailInputError.value = 0
                true
            }
            else -> {
                _emailInputError.value = 0
                true
            }
        }
    }

    fun checkPassword(): Boolean {
        return when (password.value?.checkPassword()) {
            Constants.PASSWORD_EMPTY -> {
                _passwordInputError.value = R.string.err_password_empty
                false
            }
            else -> {
                _passwordInputError.value = 0
                true
            }
        }
    }

    fun login() {
        if (checkEmail() && checkPassword()) {
            val user = User(email.value, password.value)
            if (userList.contains(user)) {
                _performLogin.value = true
            } else {
                _errorMessage.value = R.string.err_user_not_signeup
            }
        }
    }

    fun signUp() {
        if (checkEmail() && checkPassword()) {
            val user = User(email.value, password.value)
            if (userList.contains(user)) {
                _errorMessage.value = R.string.err_user_signedup
            } else {
                userList.add(user)
                login()
            }
        }
    }

    fun finishedLogin() {
        _performLogin.value = false
    }

    fun errorShowed() {
        _errorMessage.value = null
    }
}