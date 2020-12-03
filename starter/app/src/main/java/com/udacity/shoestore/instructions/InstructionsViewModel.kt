package com.udacity.shoestore.instructions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InstructionsViewModel : ViewModel() {

    private val _openShoeList = MutableLiveData<Boolean>()
    val openShoeList: LiveData<Boolean>
        get() = _openShoeList

    fun openShoeList() {
        _openShoeList.value = true
    }

    fun finishOpen() {
        _openShoeList.value = false
    }
}