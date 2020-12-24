package com.udacity.shoestore.extensions

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import java.lang.Exception

fun Fragment.getStringSafely(@StringRes resource: Int) =
    try {
        getString(resource)
    } catch (_: Exception) {
        ""
    }