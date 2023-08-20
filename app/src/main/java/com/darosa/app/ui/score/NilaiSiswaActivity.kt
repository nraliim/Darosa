package com.darosa.app.ui.score

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.darosa.app.databinding.ActivityNilaiSiswaBinding
import com.darosa.app.utils.hide
import com.darosa.app.utils.show

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
        addScore()

    }

    private fun setScore() {
        binding.apply {
//            rvNilaiSiswa.setHasFixedSize(true)
            rvNilaiSiswa.layoutManager = LinearLayoutManager(applicationContext)
            rvNilaiSiswa.adapter = adapter
        }

        viewModel.getScoreData().observe(this) { listScores ->
//            listScores.let {
//                if (it.isEmpty()) {
//                    binding.rvNilaiSiswa.hide()
//                    binding.layoutEmptyData.root.show()
//                } else {
//                    adapter.setScores(it)
//                }
//            }
            adapter.setScores(listScores)
        }
    }

    private fun addScore() {
        binding.fabAddScore.setOnClickListener {
            startActivity(Intent(this, AddNilaiActivity::class.java))
        }
    }
}