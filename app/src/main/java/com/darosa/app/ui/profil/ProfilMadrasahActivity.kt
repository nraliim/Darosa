package com.darosa.app.ui.profil

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.darosa.app.R
import com.darosa.app.databinding.ActivityProfilMadrasahBinding

class ProfilMadrasahActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfilMadrasahBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfilMadrasahBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeToolbar()
        setDataProfil()
    }

    private fun initializeToolbar() {
        binding.apply {
            setSupportActionBar(toolbar.customToolbar)
            supportActionBar?.setDisplayShowTitleEnabled(false)

            toolbar.btnBack.setOnClickListener {
                onBackPressed()
            }

            // Set judul toolbar
            toolbar.toolbarTitle.text = getString(R.string.profil_madrasah)
        }
    }

    private fun setDataProfil() {
        Glide.with(this)
            .load(R.drawable.tpq)
            .into(binding.ivProfilMadrasah)
    }
}