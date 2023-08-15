package com.darosa.app.ui.about

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.darosa.app.data.About
import com.darosa.app.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        @Suppress("DEPRECATION")
        val about = intent.getParcelableExtra<About>(EXTRA_ABOUT) as About

        initializeToolbar(about)
        setDetailAbout(about)

    }

    private fun initializeToolbar(about: About) {
        binding.apply {
            setSupportActionBar(toolbar.customToolbar)
            supportActionBar?.setDisplayShowTitleEnabled(false)

            toolbar.btnBack.setOnClickListener {
                onBackPressed()
            }

            // Set judul toolbar
            toolbar.toolbarTitle.text = about.title
        }
    }

    private fun setDetailAbout(about: About) {
        Glide.with(this)
            .load(about.photoAbout)
            .into(binding.ivPhotoAbout)
        binding.tvAbout.text = about.description
    }

    companion object{
        const val EXTRA_ABOUT = "extra_about"
    }
}