package com.darosa.app.ui.auth

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.darosa.app.R
import com.darosa.app.data.Belajar
import com.darosa.app.data.Jilid
import com.darosa.app.data.Pages
import com.darosa.app.data.User
import com.darosa.app.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        db = FirebaseFirestore.getInstance()

        register()
        onActions()
    }


    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            val checked = view.isChecked

            when (view.id) {
                R.id.rb_male ->
                    if (checked) {
                        GENDER = "Laki-laki"
                    }

                R.id.rb_female -> {
                    if (checked) {
                        GENDER = "Perempuan"
                    }
                }
            }
        }
    }

    private fun register() {

        val name = binding.etName.text
        val email = binding.etEmail.text
        val password = binding.etPassword.text
        val phone = binding.etPhoneNumber.text
        val address = binding.etAddress.text

        binding.btnRegister.setOnClickListener {

            if (TextUtils.isEmpty(name.toString())) {
                binding.etName.error = "Masukkan nama yang benar"
                return@setOnClickListener
            } else if (TextUtils.isEmpty(email.toString())) {
                binding.etEmail.error = "Masukkan email yang benar"
                return@setOnClickListener
            } else if (TextUtils.isEmpty(password.toString())) {
                binding.etPassword.error = "Masukkan kata sandi yang benar"
                return@setOnClickListener
            } else if (TextUtils.isEmpty(phone.toString())) {
                binding.etPhoneNumber.error = "Masukkan nomor hp yang benar"
                return@setOnClickListener
            } else if (TextUtils.isEmpty(address.toString())) {
                binding.etAddress.error = "Masukkan alamat yang benar"
                return@setOnClickListener
            } else {
                binding.progressBarDialog.root.visibility = View.VISIBLE
                auth.createUserWithEmailAndPassword(
                    email.toString(),
                    password.toString()
                )
                    .addOnCompleteListener(this) {
                        if (it.isSuccessful) {

                            val photoUrl =
                                "https://ui-avatars.com/api/?background=8692F7&color=fff&size=100&rounded=true&name=$name"
                            val userId = auth.currentUser!!.uid
                            val data = User(
                                name.toString(),
                                email.toString(),
                                GENDER,
                                phone.toString(),
                                address.toString(),
                                photoUrl,
                                false,
                                Belajar()
                            )
                            val userData = db.collection("user").document(userId)
                            userData.set(data)

                            val jilid0 = Jilid(
                                listOf(
                                    Pages("Halaman 1", 100),
                                    Pages("Halaman 2", 100),
                                    Pages("Halaman 3", 100),
                                    Pages("Halaman 4", 100),
                                    Pages("Halaman 5", 100),
                                    Pages("Halaman 6", 100),
                                    Pages("Halaman 7", 100),
                                    Pages("Halaman 8", 100),
                                    Pages("Halaman 9", 100),
                                    Pages("Halaman 10", 100),
                                    Pages("Halaman 11", 100),
                                    Pages("Halaman 12", 100),
                                    Pages("Halaman 13", 100),
                                    Pages("Halaman 14", 100),
                                    Pages("Halaman 15", 100),
                                    Pages("Halaman 16", 100),
                                    Pages("Halaman 17", 100),
                                    Pages("Halaman 18", 100),
                                    Pages("Halaman 19", 100),
                                    Pages("Halaman 20", 100),
                                    Pages("Halaman 21", 100),
                                    Pages("Halaman 22", 100),
                                    Pages("Halaman 23", 100),
                                    Pages("Halaman 24", 100),
                                    Pages("Halaman 25", 100),
                                    Pages("Halaman 26", 100),
                                    Pages("Halaman 27", 100),
                                    Pages("Halaman 28", 100),
                                    Pages("Halaman 29", 100),
                                    Pages("Halaman 30", 100),
                                    Pages("Halaman 31", 100),
                                    Pages("Halaman 32", 100),
                                    Pages("Halaman 33", 100),
                                    Pages("Halaman 34", 100),
                                    Pages("Halaman 35", 100),
                                    Pages("Halaman 36", 100),
                                    Pages("Halaman 37", 100),
                                    Pages("Halaman 38", 100),
                                    Pages("Halaman 39", 100),
                                    Pages("Halaman 40", 100),
                                    Pages("Halaman 41", 100),
                                    Pages("Halaman 42", 100),
                                    Pages("Halaman 43", 100),
                                    Pages("Halaman 44", 100),
                                    Pages("Halaman 45", 100),
                                    Pages("Halaman 46", 100),
                                    Pages("Halaman 47", 100),
                                    Pages("Halaman 48", 100)
                                )
                            )

                            db.collection("jilid0").document(userId).set(jilid0)

                            val jilid1 = Jilid(
                                listOf(
                                    Pages("Halaman 1", 101),
                                    Pages("Halaman 2", 101),
                                    Pages("Halaman 3", 101),
                                    Pages("Halaman 4", 101),
                                    Pages("Halaman 5", 101),
                                    Pages("Halaman 6", 101),
                                    Pages("Halaman 7", 101),
                                    Pages("Halaman 8", 101),
                                    Pages("Halaman 9", 101),
                                    Pages("Halaman 10", 101),
                                    Pages("Halaman 11", 101),
                                    Pages("Halaman 12", 101),
                                    Pages("Halaman 13", 101),
                                    Pages("Halaman 14", 101),
                                    Pages("Halaman 15", 101),
                                    Pages("Halaman 16", 101),
                                    Pages("Halaman 17", 101),
                                    Pages("Halaman 18", 101),
                                    Pages("Halaman 19", 101),
                                    Pages("Halaman 20", 101),
                                    Pages("Halaman 21", 101),
                                    Pages("Halaman 22", 101),
                                    Pages("Halaman 23", 101),
                                    Pages("Halaman 24", 101),
                                    Pages("Halaman 25", 101),
                                    Pages("Halaman 26", 101),
                                    Pages("Halaman 27", 101),
                                    Pages("Halaman 28", 101),
                                    Pages("Halaman 29", 101),
                                    Pages("Halaman 30", 101),
                                    Pages("Halaman 31", 101),
                                    Pages("Halaman 32", 101),
                                    Pages("Halaman 33", 101),
                                    Pages("Halaman 34", 101),
                                    Pages("Halaman 35", 101),
                                    Pages("Halaman 36", 101),
                                    Pages("Halaman 37", 101),
                                    Pages("Halaman 38", 101),
                                    Pages("Halaman 39", 101),
                                    Pages("Halaman 40", 101),
                                    Pages("Halaman 41", 101),
                                    Pages("Halaman 42", 101),
                                    Pages("Halaman 43", 101),
                                    Pages("Halaman 44", 101)
                                )
                            )

                            db.collection("jilid1").document(userId).set(jilid1)

                            val jilid2 = Jilid(
                                listOf(
                                    Pages("Halaman 1", 102),
                                    Pages("Halaman 2", 102),
                                    Pages("Halaman 3", 102),
                                    Pages("Halaman 4", 102),
                                    Pages("Halaman 5", 102),
                                    Pages("Halaman 6", 102),
                                    Pages("Halaman 7", 102),
                                    Pages("Halaman 8", 102),
                                    Pages("Halaman 9", 102),
                                    Pages("Halaman 10", 102),
                                    Pages("Halaman 11", 102),
                                    Pages("Halaman 12", 102),
                                    Pages("Halaman 13", 102),
                                    Pages("Halaman 14", 102),
                                    Pages("Halaman 15", 102),
                                    Pages("Halaman 16", 102),
                                    Pages("Halaman 17", 102),
                                    Pages("Halaman 18", 102),
                                    Pages("Halaman 19", 102),
                                    Pages("Halaman 20", 102),
                                    Pages("Halaman 21", 102),
                                    Pages("Halaman 22", 102),
                                    Pages("Halaman 23", 102),
                                    Pages("Halaman 24", 102),
                                    Pages("Halaman 25", 102),
                                    Pages("Halaman 26", 102),
                                    Pages("Halaman 27", 102),
                                    Pages("Halaman 28", 102),
                                    Pages("Halaman 29", 102),
                                    Pages("Halaman 30", 102),
                                    Pages("Halaman 31", 102),
                                    Pages("Halaman 32", 102),
                                    Pages("Halaman 33", 102),
                                    Pages("Halaman 34", 102),
                                    Pages("Halaman 35", 102),
                                    Pages("Halaman 36", 102),
                                    Pages("Halaman 37", 102),
                                    Pages("Halaman 38", 102),
                                    Pages("Halaman 39", 102),
                                    Pages("Halaman 40", 102),
                                    Pages("Halaman 41", 102),
                                    Pages("Halaman 42", 102),
                                    Pages("Halaman 43", 102)
                                )
                            )

                            db.collection("jilid2").document(userId).set(jilid2)

                            val jilid3 = Jilid(
                                listOf(
                                    Pages("Halaman 1", 103),
                                    Pages("Halaman 2", 103),
                                    Pages("Halaman 3", 103),
                                    Pages("Halaman 4", 103),
                                    Pages("Halaman 5", 103),
                                    Pages("Halaman 6", 103),
                                    Pages("Halaman 7", 103),
                                    Pages("Halaman 8", 103),
                                    Pages("Halaman 9", 103),
                                    Pages("Halaman 10", 103),
                                    Pages("Halaman 11", 103),
                                    Pages("Halaman 12", 103),
                                    Pages("Halaman 13", 103),
                                    Pages("Halaman 14", 103),
                                    Pages("Halaman 15", 103),
                                    Pages("Halaman 16", 103),
                                    Pages("Halaman 17", 103),
                                    Pages("Halaman 18", 103),
                                    Pages("Halaman 19", 103),
                                    Pages("Halaman 20", 103),
                                    Pages("Halaman 21", 103),
                                    Pages("Halaman 22", 103),
                                    Pages("Halaman 23", 103),
                                    Pages("Halaman 24", 103),
                                    Pages("Halaman 25", 103),
                                    Pages("Halaman 26", 103),
                                    Pages("Halaman 27", 103),
                                    Pages("Halaman 28", 103),
                                    Pages("Halaman 29", 103),
                                    Pages("Halaman 30", 103),
                                    Pages("Halaman 31", 103),
                                    Pages("Halaman 32", 103),
                                    Pages("Halaman 33", 103),
                                    Pages("Halaman 34", 103),
                                    Pages("Halaman 35", 103),
                                    Pages("Halaman 36", 103),
                                    Pages("Halaman 37", 103),
                                    Pages("Halaman 38", 103),
                                    Pages("Halaman 39", 103),
                                    Pages("Halaman 40", 103),
                                    Pages("Halaman 41", 103),
                                    Pages("Halaman 42", 103),
                                    Pages("Halaman 43", 103),
                                    Pages("Halaman 44", 103)
                                )
                            )

                            db.collection("jilid3").document(userId).set(jilid3)


                            startActivity(Intent(this, LoginActivity::class.java))
                            finish()

                            Toast.makeText(
                                this,
                                "Berhasil mendaftar, silahkan lanjut masuk",
                                Toast.LENGTH_LONG
                            ).show()
                            binding.progressBarDialog.root.visibility = View.GONE
                        } else {
                            Log.d("Register", it.toString())
                            Toast.makeText(
                                this,
                                "Gagal mendaftar, silahkan coba lagi",
                                Toast.LENGTH_LONG
                            ).show()
                            binding.progressBarDialog.root.visibility = View.GONE
                        }
                    }
            }

        }

    }

    private fun onActions() {
        binding.tvLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    companion object {
        var GENDER = ""
    }

}