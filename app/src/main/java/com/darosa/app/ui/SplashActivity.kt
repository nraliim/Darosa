package com.darosa.app.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.darosa.app.databinding.ActivitySplashBinding
import com.darosa.app.ui.dashboard.DashboardGuruActivity
import com.darosa.app.ui.dashboard.DashboardSiswaActivity
import com.darosa.app.ui.auth.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        db = FirebaseFirestore.getInstance()

        Handler(Looper.getMainLooper()).postDelayed({
            getSession()
        }, 3000)
    }

    private fun getSession() {
        if (auth.currentUser != null) {
            val userId = auth.currentUser!!.uid
            val dataUser = db.collection("user").document(userId)
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
                }
        } else {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

}