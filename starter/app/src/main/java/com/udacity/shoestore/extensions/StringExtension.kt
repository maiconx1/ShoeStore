package com.udacity.shoestore.extensions

import android.util.Patterns
import com.udacity.shoestore.util.Constants

fun String?.checkEmail(): Int {
    return if (isNullOrEmpty()) {
        Constants.FIELD_EMPTY
    } else if (this != null && !Patterns.EMAIL_ADDRESS.matcher(this).matches()) {
        Constants.FIELD_INVALID
    } else {
        Constants.FIELD_VALID
    }
}

fun String?.checkPassword(): Int {
    return if (isNullOrEmpty()) {
        Constants.FIELD_EMPTY
    } else {
        Constants.FIELD_VALID
    }
}