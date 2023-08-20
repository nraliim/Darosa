package com.darosa.app.ui.score

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.darosa.app.R
import com.darosa.app.data.Score
import com.darosa.app.databinding.ActivityEditNilaiBinding
import com.google.firebase.firestore.FirebaseFirestore

class EditNilaiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditNilaiBinding
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditNilaiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = FirebaseFirestore.getInstance()

        val score = intent.getParcelableExtra<Score>(EXTRA_SCORE) as Score

        initializeToolbar()
        setScoreData(score)
        updateScore(score)
    }

    private fun initializeToolbar() {
        binding.apply {
            setSupportActionBar(toolbar.customToolbar)
            supportActionBar?.setDisplayShowTitleEnabled(false)

            toolbar.btnBack.setOnClickListener {
                onBackPressed()
            }

            // Set judul toolbar
            toolbar.toolbarTitle.text = getString(R.string.edit_score)
        }
    }

    private fun setScoreData(score: Score) {
        binding.apply {
            tvNama.text = score.nama
            etJilid0.setText(score.jilid0.toString())
            etJilid1.setText(score.jilid1.toString())
            etJilid2.setText(score.jilid2.toString())
            etJilid3.setText(score.jilid3.toString())
        }
    }

    private fun updateScore(score: Score) {
        binding.btnSave.setOnClickListener {

            val nJilid0 = binding.etJilid0.text.toString()
            val nJilid1 = binding.etJilid1.text.toString()
            val nJilid2 = binding.etJilid2.text.toString()
            val nJilid3 = binding.etJilid3.text.toString()

            if (TextUtils.isEmpty(nJilid0)) {
                binding.etJilid0.error = "Nilai tidak boleh kosong"
                return@setOnClickListener
            } else if (TextUtils.isEmpty(nJilid1)) {
                binding.etJilid1.error = "Nilai tidak boleh kosong"
                return@setOnClickListener
            } else if (TextUtils.isEmpty(nJilid2)) {
                binding.etJilid2.error = "Nilai tidak boleh kosong"
                return@setOnClickListener
            } else if (TextUtils.isEmpty(nJilid3)) {
                binding.etJilid3.error = "Nilai tidak boleh kosong"
                return@setOnClickListener
            } else {
                binding.progressBarDialog.root.visibility = View.VISIBLE

                val userId = score.id
                val newScore = mapOf(
                    "jilid0" to nJilid0.toInt(),
                    "jilid1" to nJilid1.toInt(),
                    "jilid2" to nJilid2.toInt(),
                    "jilid3" to nJilid3.toInt()
                )

                db.collection("nilai").document(userId.toString()).update(newScore)
                    .addOnSuccessListener {
                        Toast.makeText(
                            this,
                            "Sukses Menyimpan Nilai",
                            Toast.LENGTH_SHORT
                        ).show()
                        binding.progressBarDialog.root.visibility = View.GONE
                        finish()
                    }
                    .addOnFailureListener {
                        Toast.makeText(
                            this,
                            "Gagal Menyimpan Nilai",
                            Toast.LENGTH_SHORT
                        ).show()
                        binding.progressBarDialog.root.visibility = View.GONE
                    }
            }
        }
    }

    companion object {
        const val EXTRA_SCORE = "extra_score"
    }
}