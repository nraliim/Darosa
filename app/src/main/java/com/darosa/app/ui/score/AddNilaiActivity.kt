package com.darosa.app.ui.score

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.darosa.app.R
import com.darosa.app.data.About
import com.darosa.app.data.Score
import com.darosa.app.data.User
import com.darosa.app.databinding.ActivityAddNilaiBinding
import com.darosa.app.databinding.ActivityNilaiSiswaBinding
import com.darosa.app.repository.UserRepository
import com.google.firebase.firestore.FirebaseFirestore

class AddNilaiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNilaiBinding
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNilaiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = FirebaseFirestore.getInstance()

        initializeToolbar()
        saveUserScore()
    }

    private fun initializeToolbar() {
        binding.apply {
            setSupportActionBar(toolbar.customToolbar)
            supportActionBar?.setDisplayShowTitleEnabled(false)

            toolbar.btnBack.setOnClickListener {
                onBackPressed()
            }

            // Set judul toolbar
            toolbar.toolbarTitle.text = getString(R.string.add_score)
        }
    }

    private fun saveUserScore() {
        binding.btnSave.setOnClickListener {

            val nNama = binding.etNama.text.toString()
            val nJilid0 = binding.etJilid0.text.toString()
            val nJilid1 = binding.etJilid1.text.toString()
            val nJilid2 = binding.etJilid2.text.toString()
            val nJilid3 = binding.etJilid3.text.toString()

            if (TextUtils.isEmpty(nNama)) {
                binding.etNama.error = "Nama tidak boleh kosong"
                return@setOnClickListener
            } else if (TextUtils.isEmpty(nJilid0)) {
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

                val newScore = Score(
                    null,
                    nNama,
                    nJilid0.toInt(),
                    nJilid1.toInt(),
                    nJilid2.toInt(),
                    nJilid3.toInt()
                )
                db.collection("nilai").add(newScore)
                    .addOnSuccessListener { documentReference ->
                        val generatedId = documentReference.id
                        db.collection("nilai").document(generatedId)
                            .update("id", generatedId)
                            .addOnSuccessListener {
                                Toast.makeText(
                                    this,
                                    "Sukses Menambahkan Nilai",
                                    Toast.LENGTH_SHORT
                                ).show()
                                binding.progressBarDialog.root.visibility = View.GONE
                                finish()
                            }
                            .addOnFailureListener {
                                //Do nothing
                            }
                    }
                    .addOnFailureListener {
                        Toast.makeText(
                            this,
                            "Gagal Menambahkan Nilai",
                            Toast.LENGTH_SHORT
                        ).show()
                        binding.progressBarDialog.root.visibility = View.GONE
                    }
            }
        }
    }

    companion object {
        var USER_ID = ""
        var NAME = ""
    }
}