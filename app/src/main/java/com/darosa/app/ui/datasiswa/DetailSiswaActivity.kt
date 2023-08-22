package com.darosa.app.ui.datasiswa

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.darosa.app.R
import com.darosa.app.data.Progress
import com.darosa.app.data.User
import com.darosa.app.databinding.ActivityDetailSiswaBinding
import com.darosa.app.preference.UserPreference
import com.darosa.app.ui.progress.DetailProgressActivity
import com.darosa.app.utils.loadImageUrl

class DetailSiswaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailSiswaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailSiswaBinding.inflate(layoutInflater)
        setContentView(binding.root)


        @Suppress("DEPRECATION")
        val user = intent.getParcelableExtra(EXTRA_USER) as? User

        initializeToolbar()
        getData(user)
        onActions(user)

    }

    private fun initializeToolbar() {
        binding.apply {
            setSupportActionBar(toolbar.customToolbar)
            supportActionBar?.setDisplayShowTitleEnabled(false)

            toolbar.btnBack.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }

            toolbar.toolbarTitle.text = getString(R.string.detail_siswa)
        }
    }

    private fun getData(user: User?) {
        binding.apply {
            ivPhoto.loadImageUrl(user?.fotoProfil.toString())
            tvFullname.text = user?.nama
            tvAddress.text = user?.alamat
            tvGender.text = user?.gender
            tvPhoneNumber.text = user?.nomorHp

            // Jilid Pemula
            tvProgress.text = user?.progress?.jilid0!!.toInt().toString()
            progressIndicatorBar.progress = user.progress.jilid0.toInt()

            // Jilid 1
            tvProgress1.text = user.progress.jilid1!!.toInt().toString()
            progressIndicatorBar1.progress = user.progress.jilid1.toInt()

            // Jilid 2
            tvProgress2.text = user.progress.jilid2!!.toInt().toString()
            progressIndicatorBar2.progress = user.progress.jilid2.toInt()

            // Jilid 3
            tvProgress3.text = user.progress.jilid3!!.toInt().toString()
            progressIndicatorBar3.progress = user.progress.jilid3.toInt()
        }
    }

    private fun onActions(user: User?) {
        saveUserId(user?.id.toString())
        binding.cvJilidPemula.setOnClickListener {
            val progressPemula = Progress(
                getString(R.string.yanbua_jilid_pemula),
                "jilid0",
                user
            )
            val intent = Intent(this, DetailProgressActivity::class.java)
            intent.putExtra(DetailProgressActivity.EXTRA_PROGRESS, progressPemula)
            startActivity(intent)
        }
        binding.cvJilid1.setOnClickListener {
            val progressSatu = Progress(
                getString(R.string.yanbua_jilid_1),
                "jilid1",
                user
            )
            val intent = Intent(this, DetailProgressActivity::class.java)
            intent.putExtra(DetailProgressActivity.EXTRA_PROGRESS, progressSatu)
            startActivity(intent)
        }
        binding.cvJilid2.setOnClickListener {
            val progressDua = Progress(
                getString(R.string.yanbua_jilid_2),
                "jilid2",
                user
            )
            val intent = Intent(this, DetailProgressActivity::class.java)
            intent.putExtra(DetailProgressActivity.EXTRA_PROGRESS, progressDua)
            startActivity(intent)
        }
        binding.cvJilid3.setOnClickListener {
            val progressTiga = Progress(
                getString(R.string.yanbua_jilid_3),
                "jilid3",
                user
            )
            val intent = Intent(this, DetailProgressActivity::class.java)
            intent.putExtra(DetailProgressActivity.EXTRA_PROGRESS, progressTiga)
            startActivity(intent)
        }
    }

    private fun saveUserId(userId: String) {
        val userPref = UserPreference(this)
        userPref.setUserId(userId)
    }

    companion object {
        const val EXTRA_USER = "extra_user"
    }
}