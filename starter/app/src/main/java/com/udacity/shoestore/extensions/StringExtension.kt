package com.udacity.shoestore.extensions

import android.util.Patterns
import com.udacity.shoestore.util.Constants

fun String.checkEmail(): Int {
    return if (isNullOrEmpty()) {
        Constants.EMAIL_EMPTY
    } else if (!Patterns.EMAIL_ADDRESS.matcher(this).matches()) {
        Constants.EMAIL_INVALID
    } else {
        Constants.FIELD_VALID
    }
}

fun String.checkPassword(): Int {
    return if (isNullOrEmpty()) {
        Constants.PASSWORD_EMPTY
    } else {
        Constants.FIELD_VALID
    }
}