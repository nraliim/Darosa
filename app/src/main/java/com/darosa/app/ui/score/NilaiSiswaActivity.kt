package com.darosa.app.ui.score

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.darosa.app.databinding.ActivityNilaiSiswaBinding

class NilaiSiswaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNilaiSiswaBinding
    private lateinit var viewModel: NilaiSiswaViewModel
    private lateinit var adapter: NilaiSiswaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNilaiSiswaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[NilaiSiswaViewModel::class.java]
        adapter = NilaiSiswaAdapter()

        setScore()

    }

    private fun setScore() {
        binding.apply {
            rvNilaiSiswa.setHasFixedSize(true)
            rvNilaiSiswa.layoutManager = LinearLayoutManager(applicationContext)
            rvNilaiSiswa.adapter = adapter
        }

        viewModel.allScores.observe(this) {
            adapter.setScores(it)
        }
    }
}