package com.darosa.app.ui.score

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.darosa.app.data.Score
import com.darosa.app.repository.ScoreRepository

class NilaiSiswaViewModel : ViewModel() {

    private val repository : ScoreRepository = ScoreRepository().getInstance()
    private val _allScores = MutableLiveData<List<Score>>()
    val allScores : LiveData<List<Score>> = _allScores

    init {

        repository.loadScores(_allScores)

    }
}