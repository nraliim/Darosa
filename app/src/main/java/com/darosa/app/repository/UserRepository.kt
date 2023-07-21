package com.darosa.app.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.darosa.app.data.User
import com.google.firebase.firestore.FirebaseFirestore

class UserRepository {

    private val db = FirebaseFirestore.getInstance().collection("user")

    @Volatile
    private var INSTANCE : UserRepository ?= null

    fun getInstance() : UserRepository {
        return INSTANCE ?: synchronized(this) {

            val instance = UserRepository()
            INSTANCE = instance
            instance
        }
    }

    fun loadUsers(userList: MutableLiveData<List<User>>) {

        db.whereEqualTo("isGuru", false).orderBy("nama").addSnapshotListener { snapshot, exception ->
            exception?.let {
                Log.d(TAG, it.message.toString())
                return@addSnapshotListener
            }
            snapshot?.let {
                val dataUserList = mutableListOf<User>()
                for (document in it) {
                    val user = document.toObject(User::class.java)
                    dataUserList.add(user)
                }
                userList.value = dataUserList
            }
        }

    }

    companion object {
        const val TAG = "UserRepository"
    }

}