package com.darosa.app.ui.datasiswa

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.darosa.app.data.User
import com.google.firebase.firestore.FirebaseFirestore

class SearchViewModel : ViewModel() {

    private val db = FirebaseFirestore.getInstance().collection("user")
    private val _allUsers = MutableLiveData<List<User>>()
    val allUsers: LiveData<List<User>> = _allUsers

    fun searchUser(query: String) {
        db.whereEqualTo("isGuru", false).whereEqualTo("nama", query).get()
            .addOnSuccessListener { snapshot ->

                if (snapshot != null) {
                    val dataUserList = mutableListOf<User>()
                    for (document in snapshot) {
                        val user = document.toObject(User::class.java)
                        dataUserList.add(user)
                    }
                    _allUsers.value = dataUserList
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, exception.message.toString())
            }
    }

    companion object {
        const val TAG = "SearchViewModel"
    }

}