package com.darosa.app.ui.score

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.darosa.app.data.Score
import com.google.firebase.firestore.FirebaseFirestore

class NilaiSiswaViewModel : ViewModel() {

    private val db = FirebaseFirestore.getInstance().collection("nilai")
    private val _allScores = MutableLiveData<List<Score>>()
    private val allScores : LiveData<List<Score>>  get() = _allScores

    fun getScoreData() : LiveData<List<Score>> {
        db.orderBy("nama").addSnapshotListener { snapshot, exception ->
            exception?.let {
                Log.d(TAG, it.message.toString())
                return@addSnapshotListener
            }
            snapshot?.let {
                val dataScoreList = mutableListOf<Score>()
                for (document in it) {
                    val score = document.toObject(Score::class.java)
                    dataScoreList.add(score)
                }
                _allScores.value = dataScoreList
            }
        }
        return allScores
    }

    companion object {
        const val TAG = "NilaiSiswaViewModel"
    }
}