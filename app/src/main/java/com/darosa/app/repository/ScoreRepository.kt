package com.darosa.app.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.darosa.app.data.Score
import com.google.firebase.firestore.FirebaseFirestore

class ScoreRepository {

    private val db = FirebaseFirestore.getInstance().collection("nilai")

    @Volatile
    private var INSTANCE : ScoreRepository ?= null

    fun getInstance() : ScoreRepository {
        return INSTANCE ?: synchronized(this) {

            val instance = ScoreRepository()
            INSTANCE = instance
            instance
        }
    }

    fun loadScores(scoreList: MutableLiveData<List<Score>>) {

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
                scoreList.value = dataScoreList
            }
        }

    }

    companion object {
        const val TAG = "ScoreRepository"
    }
}