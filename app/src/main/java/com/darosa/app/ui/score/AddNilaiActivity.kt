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
        loadUsers()
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

    private fun loadUsers() {
        db.collection("user").whereEqualTo("isGuru", false)
            .get()
            .addOnSuccessListener {
                val userData: MutableList<String> = mutableListOf()

                for (document in it) {
                    val id = document.id
                    val nama = document.getString("nama")
                    nama?.let {
                        userData.add(it)
                    }
                }

                val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, userData)
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.spinNama.adapter = adapter

                binding.spinNama.onItemSelectedListener =
                    object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(
                            parent: AdapterView<*>?,
                            view: View?,
                            position: Int,
                            id: Long
                        ) {
                            val name = parent?.getItemAtPosition(position).toString()
                            NAME = name
                            fetchUserId(name)
                        }

                        override fun onNothingSelected(parent: AdapterView<*>?) {
                            // Do nothing
                        }
                    }
            }
            .addOnFailureListener {
                Log.d("AddNilaiActivity", it.message.toString())
                return@addOnFailureListener
            }
    }

    private fun fetchUserId(userName: String) {
        db.collection("user")
            .whereEqualTo("nama", userName)
            .get()
            .addOnSuccessListener { querySnapshot ->
                if (!querySnapshot.isEmpty) {
                    val document = querySnapshot.documents[0]
                    val userId = document.getString("id")
                    userId?.let {
                        USER_ID = it
                    }
                }
            }
            .addOnFailureListener { exception ->
                // Handle failure
            }
    }

    private fun saveUserScore() {
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

                if (USER_ID.isNotEmpty()) {

                    val newScore = Score(
                        USER_ID,
                        NAME,
                        nJilid0.toInt(),
                        nJilid1.toInt(),
                        nJilid2.toInt(),
                        nJilid3.toInt()
                    )
                    db.collection("nilai").document(USER_ID).set(newScore)
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
    }

    companion object {
        var USER_ID = ""
        var NAME = ""
    }
}