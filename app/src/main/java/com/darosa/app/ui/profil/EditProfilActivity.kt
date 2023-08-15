package com.darosa.app.ui.profil

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.darosa.app.R
import com.darosa.app.databinding.ActivityEditProfilBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.ByteArrayOutputStream

class EditProfilActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditProfilBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var storage: StorageReference
    private var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfilBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        db = FirebaseFirestore.getInstance()
        storage = FirebaseStorage.getInstance().reference.child("photo/")

        retrieveData()
        onActions()

    }

    private fun retrieveData() {
        val userId = auth.currentUser!!.uid
        val dataUser = db.collection("user").document(userId)
        dataUser.get()
            .addOnSuccessListener {
                val fotoProfil = it.get("fotoProfil")
                val nama = it.get("nama")
                val phone = it.get("nomorHp")
                val address = it.get("alamat")
                val gender = it.get("gender")

                Glide.with(this)
                    .load(fotoProfil)
                    .centerCrop()
                    .into(binding.ivPhoto)
                binding.apply {
//                    etName.text = Editable.Factory.getInstance().newEditable(nama.toString())
//                    etPhoneNumber.text = Editable.Factory.getInstance().newEditable(phone.toString())
//                    etAddress.text = Editable.Factory.getInstance().newEditable(address.toString())
                    etName.setText(nama.toString())
                    etPhoneNumber.setText(phone.toString())
                    etAddress.setText(address.toString())
                    if (gender.toString() == "Laki-laki") {
                        rbMale.isChecked = true
                        NEW_GENDER = "Laki-laki"
                    } else {
                        rbFemale.isChecked = true
                        NEW_GENDER = "Perempuan"
                    }
                }
            }
    }

    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            val checked = view.isChecked

            when (view.id) {
                R.id.rb_male ->
                    if (checked) {
                        NEW_GENDER = "Laki-laki"
                    }

                R.id.rb_female -> {
                    if (checked) {
                        NEW_GENDER = "Perempuan"
                    }
                }
            }
        }
    }

    private fun onActions() {
        binding.apply {
            ivPhoto.setOnClickListener {
                resultLauncher.launch("image/*")
            }

            btnSave.setOnClickListener {
                progressBarDialog.root.visibility = View.VISIBLE
                if (imageUri != null) {
                    uploadImage()
                } else {
                    updateData()
                }
            }
        }
    }

    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) {
        imageUri = it
        binding.ivPhoto.setImageURI(it)
    }

    private fun uploadImage() {


        if (imageUri != null) {
            val userId = auth.currentUser!!.uid
            storage = storage.child(userId)

            // Kompres gambar sebelum mengunggah
            val bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(imageUri!!))
            val baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 30, baos)
            val data = baos.toByteArray()

            // Unggah gambar ke Firebase Storage
            storage.putBytes(data)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {

                        storage.downloadUrl
                            .addOnSuccessListener { uri ->

                                val nFotoProfil = uri.toString()
                                val nNama = binding.etName.text.toString()
                                val nPhoneNumber = binding.etPhoneNumber.text.toString()
                                val nAddress = binding.etAddress.text.toString()

                                val updateData = mapOf(
                                    "fotoProfil" to nFotoProfil,
                                    "nama" to nNama,
                                    "nomorHp" to nPhoneNumber,
                                    "gender" to NEW_GENDER,
                                    "alamat" to nAddress

                                )

                                db.collection("user").document(userId).update(updateData)

                                Toast.makeText(
                                    this,
                                    "Sukses Menyimpan Data",
                                    Toast.LENGTH_SHORT
                                ).show()
                                binding.progressBarDialog.root.visibility = View.GONE
                                finish()

                            }
                            .addOnFailureListener {
                                Toast.makeText(
                                    this,
                                    task.exception?.message,
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                    } else {
                        Toast.makeText(
                            this,
                            "Gagal Menyimpan Data",
                            Toast.LENGTH_SHORT
                        ).show()
                        binding.progressBarDialog.root.visibility = View.GONE
                    }
                }

        }

    }

    private fun updateData() {
        val userId = auth.currentUser!!.uid

        val nNama = binding.etName.text.toString()
        val nPhoneNumber = binding.etPhoneNumber.text.toString()
        val nAddress = binding.etAddress.text.toString()

        val updateData = mapOf(
            "nama" to nNama,
            "nomorHp" to nPhoneNumber,
            "gender" to NEW_GENDER,
            "alamat" to nAddress
        )
        db.collection("user").document(userId).update(updateData)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        this,
                        "Sukses Menyimpan Data",
                        Toast.LENGTH_SHORT
                    ).show()
                    binding.progressBarDialog.root.visibility = View.GONE
                    finish()
                } else {
                    Toast.makeText(
                        this,
                        "Gagal Menyimpan Data",
                        Toast.LENGTH_SHORT
                    ).show()
                    binding.progressBarDialog.root.visibility = View.GONE
                }
            }

    }

    companion object {
        var NEW_GENDER = ""
    }

}