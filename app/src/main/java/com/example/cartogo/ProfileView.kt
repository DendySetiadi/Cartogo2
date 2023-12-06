package com.example.cartogo

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProfileView : ViewModel() {
    private val _profileImageUri = MutableLiveData<Uri?>()
    val profileImageUri: LiveData<Uri?> get()= _profileImageUri

    fun updateProfileImageUri(uri: Uri) {
        _profileImageUri.value = uri
    }

    private val _editNamaValue = MutableLiveData<String>()
    val editNamaValue: LiveData<String> get() = _editNamaValue

    fun setEditNamaValue(newValue: String) {
        _editNamaValue.value = newValue
    }
}