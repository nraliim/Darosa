package com.darosa.app.ui.profil

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.darosa.app.R
import com.darosa.app.data.About
import com.darosa.app.databinding.ActivityProfilBinding
import com.darosa.app.ui.about.AboutActivity
import com.darosa.app.ui.about.AboutActivity.Companion.EXTRA_ABOUT
import com.darosa.app.ui.auth.LoginActivity
import com.darosa.app.utils.loadImageUrl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class ProfilActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfilBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    companion object {
        const val TAG = "ProfilActivity"
        const val FORM_URL = "https://forms.gle/r9CspwqMZcz9EzPSA"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfilBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        db = FirebaseFirestore.getInstance()

        // Terapkan animasi slide kiri saat membuka activity
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)

        initializeToolbar()
        retrieveData()
        onActions()
        logout()
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        // Terapkan animasi slide kanan saat menutup activity
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

    private fun initializeToolbar() {
        binding.apply {
            setSupportActionBar(toolbar.customToolbar)
            supportActionBar?.setDisplayShowTitleEnabled(false)

            toolbar.btnBack.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }

            // Set judul toolbar
            toolbar.toolbarTitle.text = getString(R.string.profil_user)
        }
    }

    private fun retrieveData() {

        val userId = auth.currentUser!!.uid
        val dataUser = db.collection("user").document(userId)
        dataUser.addSnapshotListener { snapshot, exception ->
            exception?.let {
                Log.d(TAG, it.message.toString())
                return@addSnapshotListener
            }
            snapshot?.let {
                val fotoProfil = it.get("fotoProfil")
                val nama = it.get("nama")
                val email = it.get("email")
                val phone = it.get("nomorHp")
                val address = it.get("alamat")
                val gender = it.get("gender")

                binding.apply {
                    ivPhoto.loadImageUrl(fotoProfil.toString())
                    tvNama.text = nama.toString()
                    tvEmail.text = email.toString()
                    tvPhoneNumber.text = phone.toString()
                    tvAddress.text = address.toString()
                    tvGender.text = gender.toString()
                }
            }
        }
    }


    private fun onActions() {
        let {
            binding.tvEditProfil.setOnClickListener {
                val intent = Intent(this, EditProfilActivity::class.java)
//                intent.putExtra(EXTRA_PROFIL, user)
                startActivity(intent)
            }
            binding.clAboutApps.setOnClickListener {
                val aboutApps = About(
                    getString(R.string.about_apps),
                    R.drawable.quran,
                    getString(R.string.deskripsi_apps)
                )
                val intent = Intent(this, AboutActivity::class.java)
                intent.putExtra(EXTRA_ABOUT, aboutApps)
                startActivity(intent)
            }
            binding.clAboutYanbua.setOnClickListener {
                val aboutYanbua = About(
                    getString(R.string.about_yanbua),
                    R.drawable.yanbua_1,
                    getString(R.string.deskripsi_yanbua)
                )
                val intent = Intent(this, AboutActivity::class.java)
                intent.putExtra(EXTRA_ABOUT, aboutYanbua)
                startActivity(intent)
            }
            binding.clAboutDev.setOnClickListener {
                val aboutDev = About(
                    getString(R.string.about_dev),
                    R.drawable.developer,
                    getString(R.string.deskripsi_dev)
                )
                val intent = Intent(this, AboutActivity::class.java)
                intent.putExtra(EXTRA_ABOUT, aboutDev)
                startActivity(intent)
            }
            binding.cvFeedback.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(FORM_URL))
                startActivity(intent)
            }
        }
    }

    private fun logout() {
        binding.btnLogout.setOnClickListener {
            showExitConfirmationDialog()
        }
    }

    private fun showExitConfirmationDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Konfirmasi")
            .setMessage("Apakah Anda yakin ingin keluar?")
            .setPositiveButton("Ya") { _, _ ->
                binding.progressBarDialog.root.visibility = View.VISIBLE
                auth.signOut()
                val intent = Intent(this, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                binding.progressBarDialog.root.visibility = View.GONE
            }
            .setNegativeButton("Tidak") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}