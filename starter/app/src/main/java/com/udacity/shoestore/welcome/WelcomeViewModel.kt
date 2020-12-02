package com.udacity.shoestore.welcome

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WelcomeViewModel : ViewModel() {

    private val _skipInstructions = MutableLiveData<Boolean>()
    val skipInstructions: LiveData<Boolean>
        get() = _skipInstructions

    private val _next = MutableLiveData<Boolean>()
    val next: LiveData<Boolean>
        get() = _next

    fun skip() {
        _skipInstructions.value = true
    }

    fun finishedSkip() {
        _skipInstructions.value = false
    }

    fun next() {
        _next.value = true
    }

    fun finishedNext() {
        _next.value = false
    }
}