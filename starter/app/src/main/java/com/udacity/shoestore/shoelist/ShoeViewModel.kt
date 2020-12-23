package com.udacity.shoestore.shoelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.R
import com.udacity.shoestore.models.Shoe
import java.lang.Exception

class ShoeViewModel : ViewModel() {

    // SHARED

    private val _shouldSave = MutableLiveData<Shoe?>()
    val shouldSave: LiveData<Shoe?>
        get() = _shouldSave

    // SHOE LIST

    private val shoeList = mutableListOf<Shoe>()

    var detailsIndex = -1

    private val _openDetails = MutableLiveData<Boolean>()
    val openDetails: LiveData<Boolean>
        get() = _openDetails

    private val _shoeListLiveData = MutableLiveData<List<Shoe>>()
    val shoeListLiveData: LiveData<List<Shoe>>
        get() = _shoeListLiveData

    private fun addShoe(shoe: Shoe) {
        shoeList.add(shoe)
        _shoeListLiveData.value = shoeList
    }

    fun editShoe(shoe: Shoe) {
        if (shoe != Shoe()) {
            if (detailsIndex >= 0) {
                shoeList[detailsIndex] = shoe
                _shoeListLiveData.value = shoeList
                detailsIndex = -1
            } else {
                addShoe(shoe)
            }
        }
    }

    fun openShoeDetail() {
        _openDetails.value = true
    }

    fun finishOpenDetails() {
        _openDetails.value = false
    }

    // SHOE DETAILS

    private val _shoe = MutableLiveData<Shoe>()
    val shoe: LiveData<Shoe>
        get() = _shoe

    private val _nameInputError = MutableLiveData<Int>()
    val nameInputError: LiveData<Int>
        get() = _nameInputError

    private val _companyInputError = MutableLiveData<Int>()
    val companyInputError: LiveData<Int>
        get() = _companyInputError

    private val _sizeInputError = MutableLiveData<Int>()
    val sizeInputError: LiveData<Int>
        get() = _sizeInputError

    var company = MutableLiveData<String>()
    var name = MutableLiveData<String>()
    var size = MutableLiveData<String>()
    var description = MutableLiveData<String>()

    fun setValues(shoe: Shoe?) {
        company.value = shoe?.company
        name.value = shoe?.name
        size.value = if (shoe?.size == null) null else shoe.size.toString()
        description.value = shoe?.description
    }

    fun save() {
        if (checkCompany() && checkName() && checkSize()) {
            _shouldSave.value = Shoe(
                name.value ?: "",
                (size.value ?: "0").toDouble(),
                company.value ?: "",
                description.value ?: ""
            )
        }
    }

    fun cancel() {
        _shouldSave.value = Shoe()
    }

    private fun checkName(): Boolean {
        val checked = !name.value.isNullOrEmpty()
        if (!checked) {
            _nameInputError.value = R.string.err_field_invalid
        } else {
            _nameInputError.value = 0
        }
        return checked
    }

    private fun checkCompany(): Boolean {
        val checked = !company.value.isNullOrEmpty()
        if (!checked) {
            _companyInputError.value = R.string.err_field_invalid
        } else {
            _companyInputError.value = 0
        }
        return checked
    }

    private fun checkSize(): Boolean {
        val checked = try {
            !(size.value.isNullOrEmpty() || size.value.toString().toDouble() <= 0)
        } catch (_: Exception) {
            false
        }
        if (!checked) {
            _sizeInputError.value = R.string.err_field_invalid
        } else {
            _sizeInputError.value = 0
        }
        return checked
    }

    fun finishShouldSave() {
        _shouldSave.value = null
    }
}