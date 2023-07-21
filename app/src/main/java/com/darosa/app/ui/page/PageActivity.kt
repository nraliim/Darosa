package com.darosa.app.ui.page

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.darosa.app.R
import com.darosa.app.data.Pages
import com.darosa.app.databinding.ActivityPageBinding
import com.darosa.app.utils.hide
import com.darosa.app.utils.show
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.util.Util
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class PageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPageBinding
    private var player: ExoPlayer? = null
    private val userId = FirebaseAuth.getInstance().currentUser!!.uid
    private val db = FirebaseFirestore.getInstance()

    companion object {
        const val TAG = "PageActivity"
        const val EXTRA_PAGES = "extra_pages"
        const val JILID_PEMULA = 100L
        const val JILID_SATU = 101L
        const val JILID_DUA = 102L
        const val JILID_TIGA = 103L
        var URL_AUDIO = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        @Suppress("DEPRECATION")
        val pages = intent.getParcelableExtra(EXTRA_PAGES) as? Pages

        initializeToolbar(pages?.halaman)
        initializeData(pages!!)

    }

    private fun initializeToolbar(title: String?) {
        binding.apply {
            setSupportActionBar(toolbar.customToolbar)
            supportActionBar?.setDisplayShowTitleEnabled(false)

            toolbar.btnBack.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }

            toolbar.toolbarTitle.text = title
        }
    }

    private fun setMedia(materiUrl: String, audioUrl: String) {
        Glide.with(this)
            .load(materiUrl)
            .into(binding.ivMateri)
        URL_AUDIO = audioUrl
    }

    private fun initializeData(pages: Pages) {
        binding.apply {
            progressBarDialog.root.show()
            if (pages.isChecked == false) {
                btnDone.isClickable = true
                btnDone.isEnabled = true
            } else {
                btnDone.isClickable = false
                btnDone.isEnabled = false
            }

        }
        when (pages.code!!) {
            JILID_PEMULA -> {
                binding.btnDone.setOnClickListener {
                    updateValue("jilid0", pages.halaman.toString(), 2.09)
                }
                when (pages.halaman.toString()) {
                    "Halaman 1" -> setMedia(PageRepository.MATERI_0_01, PageRepository.AUDIO_0_01)
                    "Halaman 2" -> setMedia(PageRepository.MATERI_0_02, PageRepository.AUDIO_0_02)
                    "Halaman 3" -> setMedia(PageRepository.MATERI_0_03, PageRepository.AUDIO_0_03)
                    "Halaman 4" -> setMedia(PageRepository.MATERI_0_04, PageRepository.AUDIO_0_04)
                    "Halaman 5" -> setMedia(PageRepository.MATERI_0_05, PageRepository.AUDIO_0_05)
                    "Halaman 6" -> setMedia(PageRepository.MATERI_0_06, PageRepository.AUDIO_0_06)
                    "Halaman 7" -> setMedia(PageRepository.MATERI_0_07, PageRepository.AUDIO_0_07)
                    "Halaman 8" -> setMedia(PageRepository.MATERI_0_08, PageRepository.AUDIO_0_08)
                    "Halaman 9" -> setMedia(PageRepository.MATERI_0_09, PageRepository.AUDIO_0_09)
                    "Halaman 10" -> setMedia(PageRepository.MATERI_0_10, PageRepository.AUDIO_0_10)
                    "Halaman 11" -> setMedia(PageRepository.MATERI_0_11, PageRepository.AUDIO_0_11)
                    "Halaman 12" -> setMedia(PageRepository.MATERI_0_12, PageRepository.AUDIO_0_12)
                    "Halaman 13" -> setMedia(PageRepository.MATERI_0_13, PageRepository.AUDIO_0_13)
                    "Halaman 14" -> setMedia(PageRepository.MATERI_0_14, PageRepository.AUDIO_0_14)
                    "Halaman 15" -> setMedia(PageRepository.MATERI_0_15, PageRepository.AUDIO_0_15)
                    "Halaman 16" -> setMedia(PageRepository.MATERI_0_16, PageRepository.AUDIO_0_16)
                    "Halaman 17" -> setMedia(PageRepository.MATERI_0_17, PageRepository.AUDIO_0_17)
                    "Halaman 18" -> setMedia(PageRepository.MATERI_0_18, PageRepository.AUDIO_0_18)
                    "Halaman 19" -> setMedia(PageRepository.MATERI_0_19, PageRepository.AUDIO_0_19)
                    "Halaman 20" -> setMedia(PageRepository.MATERI_0_20, PageRepository.AUDIO_0_20)
                    "Halaman 21" -> setMedia(PageRepository.MATERI_0_21, PageRepository.AUDIO_0_21)
                    "Halaman 22" -> setMedia(PageRepository.MATERI_0_22, PageRepository.AUDIO_0_22)
                    "Halaman 23" -> setMedia(PageRepository.MATERI_0_23, PageRepository.AUDIO_0_23)
                    "Halaman 24" -> setMedia(PageRepository.MATERI_0_24, PageRepository.AUDIO_0_24)
                    "Halaman 25" -> setMedia(PageRepository.MATERI_0_25, PageRepository.AUDIO_0_25)
                    "Halaman 26" -> setMedia(PageRepository.MATERI_0_26, PageRepository.AUDIO_0_26)
                    "Halaman 27" -> setMedia(PageRepository.MATERI_0_27, PageRepository.AUDIO_0_27)
                    "Halaman 28" -> setMedia(PageRepository.MATERI_0_28, PageRepository.AUDIO_0_28)
                    "Halaman 29" -> setMedia(PageRepository.MATERI_0_29, PageRepository.AUDIO_0_29)
                    "Halaman 30" -> setMedia(PageRepository.MATERI_0_30, PageRepository.AUDIO_0_30)
                    "Halaman 31" -> setMedia(PageRepository.MATERI_0_31, PageRepository.AUDIO_0_31)
                    "Halaman 32" -> setMedia(PageRepository.MATERI_0_32, PageRepository.AUDIO_0_32)
                    "Halaman 33" -> setMedia(PageRepository.MATERI_0_33, PageRepository.AUDIO_0_33)
                    "Halaman 34" -> setMedia(PageRepository.MATERI_0_34, PageRepository.AUDIO_0_34)
                    "Halaman 35" -> setMedia(PageRepository.MATERI_0_35, PageRepository.AUDIO_0_35)
                    "Halaman 36" -> setMedia(PageRepository.MATERI_0_36, PageRepository.AUDIO_0_36)
                    "Halaman 37" -> setMedia(PageRepository.MATERI_0_37, PageRepository.AUDIO_0_37)
                    "Halaman 38" -> setMedia(PageRepository.MATERI_0_38, PageRepository.AUDIO_0_38)
                    "Halaman 39" -> setMedia(PageRepository.MATERI_0_39, PageRepository.AUDIO_0_39)
                    "Halaman 40" -> setMedia(PageRepository.MATERI_0_40, PageRepository.AUDIO_0_40)
                    "Halaman 41" -> setMedia(PageRepository.MATERI_0_41, PageRepository.AUDIO_0_41)
                    "Halaman 42" -> setMedia(PageRepository.MATERI_0_42, PageRepository.AUDIO_0_42)
                    "Halaman 43" -> setMedia(PageRepository.MATERI_0_43, PageRepository.AUDIO_0_43)
                    "Halaman 44" -> setMedia(PageRepository.MATERI_0_44, PageRepository.AUDIO_0_44)
                    "Halaman 45" -> setMedia(PageRepository.MATERI_0_45, PageRepository.AUDIO_0_45)
                    "Halaman 46" -> setMedia(PageRepository.MATERI_0_46, PageRepository.AUDIO_0_46)
                    "Halaman 47" -> setMedia(PageRepository.MATERI_0_47, PageRepository.AUDIO_0_47)
                    "Halaman 48" -> setMedia(PageRepository.MATERI_0_48, PageRepository.AUDIO_0_48)
                }
                binding.progressBarDialog.root.hide()
            }

            JILID_SATU -> {
                binding.btnDone.setOnClickListener {
                    updateValue("jilid1", pages.halaman.toString(), 2.28)
                }
                when (pages.halaman.toString()) {
                    "Halaman 1" -> setMedia(PageRepository.MATERI_1_01, PageRepository.AUDIO_1_01)
                    "Halaman 2" -> setMedia(PageRepository.MATERI_1_02, PageRepository.AUDIO_1_02)
                    "Halaman 3" -> setMedia(PageRepository.MATERI_1_03, PageRepository.AUDIO_1_03)
                    "Halaman 4" -> setMedia(PageRepository.MATERI_1_04, PageRepository.AUDIO_1_04)
                    "Halaman 5" -> setMedia(PageRepository.MATERI_1_05, PageRepository.AUDIO_1_05)
                    "Halaman 6" -> setMedia(PageRepository.MATERI_1_06, PageRepository.AUDIO_1_06)
                    "Halaman 7" -> setMedia(PageRepository.MATERI_1_07, PageRepository.AUDIO_1_07)
                    "Halaman 8" -> setMedia(PageRepository.MATERI_1_08, PageRepository.AUDIO_1_08)
                    "Halaman 9" -> setMedia(PageRepository.MATERI_1_09, PageRepository.AUDIO_1_09)
                    "Halaman 10" -> setMedia(PageRepository.MATERI_1_10, PageRepository.AUDIO_1_10)
                    "Halaman 11" -> setMedia(PageRepository.MATERI_1_11, PageRepository.AUDIO_1_11)
                    "Halaman 12" -> setMedia(PageRepository.MATERI_1_12, PageRepository.AUDIO_1_12)
                    "Halaman 13" -> setMedia(PageRepository.MATERI_1_13, PageRepository.AUDIO_1_13)
                    "Halaman 14" -> setMedia(PageRepository.MATERI_1_14, PageRepository.AUDIO_1_14)
                    "Halaman 15" -> setMedia(PageRepository.MATERI_1_15, PageRepository.AUDIO_1_15)
                    "Halaman 16" -> setMedia(PageRepository.MATERI_1_16, PageRepository.AUDIO_1_16)
                    "Halaman 17" -> setMedia(PageRepository.MATERI_1_17, PageRepository.AUDIO_1_17)
                    "Halaman 18" -> setMedia(PageRepository.MATERI_1_18, PageRepository.AUDIO_1_18)
                    "Halaman 19" -> setMedia(PageRepository.MATERI_1_19, PageRepository.AUDIO_1_19)
                    "Halaman 20" -> setMedia(PageRepository.MATERI_1_20, PageRepository.AUDIO_1_20)
                    "Halaman 21" -> setMedia(PageRepository.MATERI_1_21, PageRepository.AUDIO_1_21)
                    "Halaman 22" -> setMedia(PageRepository.MATERI_1_22, PageRepository.AUDIO_1_22)
                    "Halaman 23" -> setMedia(PageRepository.MATERI_1_23, PageRepository.AUDIO_1_23)
                    "Halaman 24" -> setMedia(PageRepository.MATERI_1_24, PageRepository.AUDIO_1_24)
                    "Halaman 25" -> setMedia(PageRepository.MATERI_1_25, PageRepository.AUDIO_1_25)
                    "Halaman 26" -> setMedia(PageRepository.MATERI_1_26, PageRepository.AUDIO_1_26)
                    "Halaman 27" -> setMedia(PageRepository.MATERI_1_27, PageRepository.AUDIO_1_27)
                    "Halaman 28" -> setMedia(PageRepository.MATERI_1_28, PageRepository.AUDIO_1_28)
                    "Halaman 29" -> setMedia(PageRepository.MATERI_1_29, PageRepository.AUDIO_1_29)
                    "Halaman 30" -> setMedia(PageRepository.MATERI_1_30, PageRepository.AUDIO_1_30)
                    "Halaman 31" -> setMedia(PageRepository.MATERI_1_31, PageRepository.AUDIO_1_31)
                    "Halaman 32" -> setMedia(PageRepository.MATERI_1_32, PageRepository.AUDIO_1_32)
                    "Halaman 33" -> setMedia(PageRepository.MATERI_1_33, PageRepository.AUDIO_1_33)
                    "Halaman 34" -> setMedia(PageRepository.MATERI_1_34, PageRepository.AUDIO_1_34)
                    "Halaman 35" -> setMedia(PageRepository.MATERI_1_35, PageRepository.AUDIO_1_35)
                    "Halaman 36" -> setMedia(PageRepository.MATERI_1_36, PageRepository.AUDIO_1_36)
                    "Halaman 37" -> setMedia(PageRepository.MATERI_1_37, PageRepository.AUDIO_1_37)
                    "Halaman 38" -> setMedia(PageRepository.MATERI_1_38, PageRepository.AUDIO_1_38)
                    "Halaman 39" -> setMedia(PageRepository.MATERI_1_39, PageRepository.AUDIO_1_39)
                    "Halaman 40" -> setMedia(PageRepository.MATERI_1_40, PageRepository.AUDIO_1_40)
                    "Halaman 41" -> setMedia(PageRepository.MATERI_1_41, PageRepository.AUDIO_1_41)
                    "Halaman 42" -> setMedia(PageRepository.MATERI_1_42, PageRepository.AUDIO_1_42)
                    "Halaman 43" -> setMedia(PageRepository.MATERI_1_43, PageRepository.AUDIO_1_43)
                    "Halaman 44" -> setMedia(PageRepository.MATERI_1_44, PageRepository.AUDIO_1_44)
                }
                binding.progressBarDialog.root.hide()
            }

            JILID_DUA -> {
                binding.btnDone.setOnClickListener {
                    updateValue("jilid2", pages.halaman.toString(), 2.33)
                }
                when (pages.halaman.toString()) {
                    "Halaman 1" -> setMedia(PageRepository.MATERI_2_01, PageRepository.AUDIO_2_01)
                    "Halaman 2" -> setMedia(PageRepository.MATERI_2_02, PageRepository.AUDIO_2_02)
                    "Halaman 3" -> setMedia(PageRepository.MATERI_2_03, PageRepository.AUDIO_2_03)
                    "Halaman 4" -> setMedia(PageRepository.MATERI_2_04, PageRepository.AUDIO_2_04)
                    "Halaman 5" -> setMedia(PageRepository.MATERI_2_05, PageRepository.AUDIO_2_05)
                    "Halaman 6" -> setMedia(PageRepository.MATERI_2_06, PageRepository.AUDIO_2_06)
                    "Halaman 7" -> setMedia(PageRepository.MATERI_2_07, PageRepository.AUDIO_2_07)
                    "Halaman 8" -> setMedia(PageRepository.MATERI_2_08, PageRepository.AUDIO_2_08)
                    "Halaman 9" -> setMedia(PageRepository.MATERI_2_09, PageRepository.AUDIO_2_09)
                    "Halaman 10" -> setMedia(PageRepository.MATERI_2_10, PageRepository.AUDIO_2_10)
                    "Halaman 11" -> setMedia(PageRepository.MATERI_2_11, PageRepository.AUDIO_2_11)
                    "Halaman 12" -> setMedia(PageRepository.MATERI_2_12, PageRepository.AUDIO_2_12)
                    "Halaman 13" -> setMedia(PageRepository.MATERI_2_13, PageRepository.AUDIO_2_13)
                    "Halaman 14" -> setMedia(PageRepository.MATERI_2_14, PageRepository.AUDIO_2_14)
                    "Halaman 15" -> setMedia(PageRepository.MATERI_2_15, PageRepository.AUDIO_2_15)
                    "Halaman 16" -> setMedia(PageRepository.MATERI_2_16, PageRepository.AUDIO_2_16)
                    "Halaman 17" -> setMedia(PageRepository.MATERI_2_17, PageRepository.AUDIO_2_17)
                    "Halaman 18" -> setMedia(PageRepository.MATERI_2_18, PageRepository.AUDIO_2_18)
                    "Halaman 19" -> setMedia(PageRepository.MATERI_2_19, PageRepository.AUDIO_2_19)
                    "Halaman 20" -> setMedia(PageRepository.MATERI_2_20, PageRepository.AUDIO_2_20)
                    "Halaman 21" -> setMedia(PageRepository.MATERI_2_21, PageRepository.AUDIO_2_21)
                    "Halaman 22" -> setMedia(PageRepository.MATERI_2_22, PageRepository.AUDIO_2_22)
                    "Halaman 23" -> setMedia(PageRepository.MATERI_2_23, PageRepository.AUDIO_2_23)
                    "Halaman 24" -> setMedia(PageRepository.MATERI_2_24, PageRepository.AUDIO_2_24)
                    "Halaman 25" -> setMedia(PageRepository.MATERI_2_25, PageRepository.AUDIO_2_25)
                    "Halaman 26" -> setMedia(PageRepository.MATERI_2_26, PageRepository.AUDIO_2_26)
                    "Halaman 27" -> setMedia(PageRepository.MATERI_2_27, PageRepository.AUDIO_2_27)
                    "Halaman 28" -> setMedia(PageRepository.MATERI_2_28, PageRepository.AUDIO_2_28)
                    "Halaman 29" -> setMedia(PageRepository.MATERI_2_29, PageRepository.AUDIO_2_29)
                    "Halaman 30" -> setMedia(PageRepository.MATERI_2_30, PageRepository.AUDIO_2_30)
                    "Halaman 31" -> setMedia(PageRepository.MATERI_2_31, PageRepository.AUDIO_2_31)
                    "Halaman 32" -> setMedia(PageRepository.MATERI_2_32, PageRepository.AUDIO_2_32)
                    "Halaman 33" -> setMedia(PageRepository.MATERI_2_33, PageRepository.AUDIO_2_33)
                    "Halaman 34" -> setMedia(PageRepository.MATERI_2_34, PageRepository.AUDIO_2_34)
                    "Halaman 35" -> setMedia(PageRepository.MATERI_2_35, PageRepository.AUDIO_2_35)
                    "Halaman 36" -> setMedia(PageRepository.MATERI_2_36, PageRepository.AUDIO_2_36)
                    "Halaman 37" -> setMedia(PageRepository.MATERI_2_37, PageRepository.AUDIO_2_37)
                    "Halaman 38" -> setMedia(PageRepository.MATERI_2_38, PageRepository.AUDIO_2_38)
                    "Halaman 39" -> setMedia(PageRepository.MATERI_2_39, PageRepository.AUDIO_2_39)
                    "Halaman 40" -> setMedia(PageRepository.MATERI_2_40, PageRepository.AUDIO_2_40)
                    "Halaman 41" -> setMedia(PageRepository.MATERI_2_41, PageRepository.AUDIO_2_41)
                    "Halaman 42" -> setMedia(PageRepository.MATERI_2_42, PageRepository.AUDIO_2_42)
                    "Halaman 43" -> setMedia(PageRepository.MATERI_2_43, PageRepository.AUDIO_2_43)
                }
                binding.progressBarDialog.root.hide()

            }

            JILID_TIGA -> {
                binding.btnDone.setOnClickListener {
                    updateValue("jilid3", pages.halaman.toString(), 2.28)
                }
                when (pages.halaman.toString()) {
                    "Halaman 1" -> setMedia(PageRepository.MATERI_3_01, PageRepository.AUDIO_3_01)
                    "Halaman 2" -> setMedia(PageRepository.MATERI_3_02, PageRepository.AUDIO_3_02)
                    "Halaman 3" -> setMedia(PageRepository.MATERI_3_03, PageRepository.AUDIO_3_03)
                    "Halaman 4" -> setMedia(PageRepository.MATERI_3_04, PageRepository.AUDIO_3_04)
                    "Halaman 5" -> setMedia(PageRepository.MATERI_3_05, PageRepository.AUDIO_3_05)
                    "Halaman 6" -> setMedia(PageRepository.MATERI_3_06, PageRepository.AUDIO_3_06)
                    "Halaman 7" -> setMedia(PageRepository.MATERI_3_07, PageRepository.AUDIO_3_07)
                    "Halaman 8" -> setMedia(PageRepository.MATERI_3_08, PageRepository.AUDIO_3_08)
                    "Halaman 9" -> setMedia(PageRepository.MATERI_3_09, PageRepository.AUDIO_3_09)
                    "Halaman 10" -> setMedia(PageRepository.MATERI_3_10, PageRepository.AUDIO_3_10)
                    "Halaman 11" -> setMedia(PageRepository.MATERI_3_11, PageRepository.AUDIO_3_11)
                    "Halaman 12" -> setMedia(PageRepository.MATERI_3_12, PageRepository.AUDIO_3_12)
                    "Halaman 13" -> setMedia(PageRepository.MATERI_3_13, PageRepository.AUDIO_3_13)
                    "Halaman 14" -> setMedia(PageRepository.MATERI_3_14, PageRepository.AUDIO_3_14)
                    "Halaman 15" -> setMedia(PageRepository.MATERI_3_15, PageRepository.AUDIO_3_15)
                    "Halaman 16" -> setMedia(PageRepository.MATERI_3_16, PageRepository.AUDIO_3_16)
                    "Halaman 17" -> setMedia(PageRepository.MATERI_3_17, PageRepository.AUDIO_3_17)
                    "Halaman 18" -> setMedia(PageRepository.MATERI_3_18, PageRepository.AUDIO_3_18)
                    "Halaman 19" -> setMedia(PageRepository.MATERI_3_19, PageRepository.AUDIO_3_19)
                    "Halaman 20" -> setMedia(PageRepository.MATERI_3_20, PageRepository.AUDIO_3_20)
                    "Halaman 21" -> setMedia(PageRepository.MATERI_3_21, PageRepository.AUDIO_3_21)
                    "Halaman 22" -> setMedia(PageRepository.MATERI_3_22, PageRepository.AUDIO_3_22)
                    "Halaman 23" -> setMedia(PageRepository.MATERI_3_23, PageRepository.AUDIO_3_23)
                    "Halaman 24" -> setMedia(PageRepository.MATERI_3_24, PageRepository.AUDIO_3_24)
                    "Halaman 25" -> setMedia(PageRepository.MATERI_3_25, PageRepository.AUDIO_3_25)
                    "Halaman 26" -> setMedia(PageRepository.MATERI_3_26, PageRepository.AUDIO_3_26)
                    "Halaman 27" -> setMedia(PageRepository.MATERI_3_27, PageRepository.AUDIO_3_27)
                    "Halaman 28" -> setMedia(PageRepository.MATERI_3_28, PageRepository.AUDIO_3_28)
                    "Halaman 29" -> setMedia(PageRepository.MATERI_3_29, PageRepository.AUDIO_3_29)
                    "Halaman 30" -> setMedia(PageRepository.MATERI_3_30, PageRepository.AUDIO_3_30)
                    "Halaman 31" -> setMedia(PageRepository.MATERI_3_31, PageRepository.AUDIO_3_31)
                    "Halaman 32" -> setMedia(PageRepository.MATERI_3_32, PageRepository.AUDIO_3_32)
                    "Halaman 33" -> setMedia(PageRepository.MATERI_3_33, PageRepository.AUDIO_3_33)
                    "Halaman 34" -> setMedia(PageRepository.MATERI_3_34, PageRepository.AUDIO_3_34)
                    "Halaman 35" -> setMedia(PageRepository.MATERI_3_35, PageRepository.AUDIO_3_35)
                    "Halaman 36" -> setMedia(PageRepository.MATERI_3_36, PageRepository.AUDIO_3_36)
                    "Halaman 37" -> setMedia(PageRepository.MATERI_3_37, PageRepository.AUDIO_3_37)
                    "Halaman 38" -> setMedia(PageRepository.MATERI_3_38, PageRepository.AUDIO_3_38)
                    "Halaman 39" -> setMedia(PageRepository.MATERI_3_39, PageRepository.AUDIO_3_39)
                    "Halaman 40" -> setMedia(PageRepository.MATERI_3_40, PageRepository.AUDIO_3_40)
                    "Halaman 41" -> setMedia(PageRepository.MATERI_3_41, PageRepository.AUDIO_3_41)
                    "Halaman 42" -> setMedia(PageRepository.MATERI_3_42, PageRepository.AUDIO_3_42)
                    "Halaman 43" -> setMedia(PageRepository.MATERI_3_43, PageRepository.AUDIO_3_43)
                    "Halaman 44" -> setMedia(PageRepository.MATERI_3_44, PageRepository.AUDIO_3_44)
                }
                binding.progressBarDialog.root.hide()
            }
        }
    }

    private fun initializePlayer() {
        val mediaItem = MediaItem.fromUri(URL_AUDIO)
        player = ExoPlayer.Builder(this).build().also { exoPlayer ->
            binding.apply {
                exoAudio.player = exoPlayer
            }
            exoPlayer.setMediaItem(mediaItem)
            exoPlayer.prepare()
        }
    }

    private fun releasePlayer() {
        player?.release()
        player = null
    }

    private fun updateValue(jilid: String, halaman: String, progress: Double) {
        binding.progressBarDialog.root.show()
        val documentRef = db.collection(jilid).document(userId)
        documentRef.get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    val pages = documentSnapshot.get("pages") as? List<Map<String, Any>>?
                    if (pages != null) {
                        val updatedPages = pages.map {
                            if (it["halaman"] == halaman) {
                                it.toMutableMap().apply {
                                    this["isChecked"] = true
                                }
                            } else {
                                it
                            }
                        }
                        documentRef.update("pages", updatedPages)
                            .addOnSuccessListener {
                                updateProgress(jilid, progress)
                            }
                            .addOnFailureListener {
                                Log.d(TAG, "Gagal update isChecked menjadi true")
                            }
                    }
                }
            }
            .addOnFailureListener {
                Log.d(TAG, it.message.toString())
            }
    }

    private fun updateProgress(tipeJilid: String, number: Double) {
        val documentRef = db.collection("user").document(userId)
        documentRef.get()
            .addOnSuccessListener { snapshot ->
                if (snapshot.exists()) {
                    //mendapatkan nilai awal progress
                    val progress = snapshot["progress"] as Map<*, *>
                    if (progress.isNotEmpty()) {
                        val oldProgress = progress[tipeJilid] as Double
                        val newProgress = oldProgress + number

                        val updatedProgress = progress.toMutableMap()
                        updatedProgress[tipeJilid] = newProgress

                        documentRef.update("progress", updatedProgress)
                            .addOnSuccessListener {
                                Log.d(TAG, "Sukses update isChecked dan progress siswa")
                                Toast.makeText(
                                    this,
                                    "Halaman sudah selesai, silahkan lanjut halaman berikutnya",
                                    Toast.LENGTH_SHORT
                                ).show()
                                binding.progressBarDialog.root.hide()
                                finish()
                            }
                            .addOnFailureListener {
                                Log.d(TAG, "Gagal update progress siswa")
                            }
                    }
                }
            }
            .addOnFailureListener {
                Log.d(TAG, it.message.toString())
            }
    }

    public override fun onStart() {
        super.onStart()
        if (Util.SDK_INT >= 24) {
            initializePlayer()
        }
    }

    public override fun onResume() {
        super.onResume()
        if ((Util.SDK_INT < 24 || player == null)) {
            initializePlayer()
        }
    }

    public override fun onPause() {
        super.onPause()
        if (Util.SDK_INT < 24) {
            releasePlayer()
        }
    }

    public override fun onStop() {
        super.onStop()
        if (Util.SDK_INT >= 24) {
            releasePlayer()
        }
    }
}