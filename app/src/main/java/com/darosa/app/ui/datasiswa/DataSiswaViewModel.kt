package com.darosa.app.ui.datasiswa

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.darosa.app.data.User
import com.darosa.app.repository.UserRepository

class DataSiswaViewModel : ViewModel() {

    private val repository : UserRepository = UserRepository().getInstance()
    private val _allUsers = MutableLiveData<List<User>>()
    val allUsers : LiveData<List<User>> = _allUsers

    init {

        repository.loadUsers(_allUsers)

    }

}