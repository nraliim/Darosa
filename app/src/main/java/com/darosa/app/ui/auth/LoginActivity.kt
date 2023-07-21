package com.darosa.app.ui.auth

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.darosa.app.R
import com.darosa.app.databinding.ActivityLoginBinding
import com.darosa.app.ui.dashboard.DashboardGuruActivity
import com.darosa.app.ui.dashboard.DashboardSiswaActivity
import com.darosa.app.ui.dashboard.NetworkConnectionLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var networkConnection: NetworkConnectionLiveData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        networkConnection = NetworkConnectionLiveData(this)
        networkConnection.observe(this) { isInternetAvailable ->
            if (isInternetAvailable) {
                setContentView(binding.root)
            } else {
                setContentView(R.layout.network_error)
            }
        }

        auth = Firebase.auth
        db = FirebaseFirestore.getInstance()

        login()
        onActions()
    }

    private fun login() {
        binding.btnLogin.setOnClickListener{
            if (TextUtils.isEmpty(binding.etEmail.text.toString())) {
                binding.etEmail.error = "Masukkan email yang benar"
                return@setOnClickListener
            } else if (TextUtils.isEmpty(binding.etPassword.text.toString())) {
                binding.etPassword.error = "Masukkan kata sandi yang benar"
            } else {
                binding.progressBarDialog.root.visibility = View.VISIBLE
                auth.signInWithEmailAndPassword(
                    binding.etEmail.text.toString().trim(),
                    binding.etPassword.text.toString()
                )
                    .addOnCompleteListener { it ->
                        if (it.isSuccessful) {

                            val userId = auth.currentUser?.uid
                            val dataUser = db.collection("user").document(userId!!)
                            dataUser.get()
                                .addOnSuccessListener {

                                    val status = it.get("isGuru")
                                    if (status == true) {
                                        startActivity(Intent(this, DashboardGuruActivity::class.java))
                                        finish()
                                    } else {
                                        startActivity(Intent(this, DashboardSiswaActivity::class.java))
                                        finish()
                                    }
                                    binding.progressBarDialog.root.visibility = View.GONE
                                }
                        } else {
                            Toast.makeText(
                                this,
                                "Login gagal, silahkan coba lagi",
                                Toast.LENGTH_LONG
                            ).show()
                            binding.progressBarDialog.root.visibility = View.GONE
                        }
                    }
            }
        }
    }

    private fun onActions() {
        binding.tvRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }
    }
}