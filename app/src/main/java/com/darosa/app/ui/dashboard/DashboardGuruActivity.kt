package com.darosa.app.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.darosa.app.R
import com.darosa.app.databinding.ActivityDashboardGuruBinding
import com.darosa.app.ui.datasiswa.DataSiswaActivity
import com.darosa.app.ui.profil.ProfilActivity
import com.darosa.app.ui.profil.ProfilMadrasahActivity
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class DashboardGuruActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardGuruBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var networkConnection: NetworkConnectionLiveData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardGuruBinding.inflate(layoutInflater)
        networkConnection = NetworkConnectionLiveData(this)
        networkConnection.observe(this) { isInternetAvailable ->
            if (isInternetAvailable) {
                setContentView(binding.root)
            } else {
                setContentView(R.layout.network_error)
            }
        }

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        imageSlideShow()
        retrieveData()
        onActions()
    }

    private fun onActions() {
        binding.ivPhoto.setOnClickListener {
            startActivity(Intent(this, ProfilActivity::class.java))
        }
        binding.cvDataSiswa.setOnClickListener {
            startActivity(Intent(this, DataSiswaActivity::class.java))
        }
        binding.cvProfilMadrasah.setOnClickListener {
            startActivity(Intent(this, ProfilMadrasahActivity::class.java))
        }
    }

    private fun imageSlideShow() {
        val imageSlider = binding.imgSlider
        val imageList = ArrayList<SlideModel>()

        imageList.add(SlideModel(R.drawable.greeting))
        imageList.add(SlideModel(R.drawable.ayo_mengaji))
        imageList.add(SlideModel(R.drawable.semangat))

        imageSlider.setImageList(imageList, ScaleTypes.CENTER_CROP)
    }

    private fun retrieveData() {
        val userId = auth.currentUser!!.uid
        val dataUser = db.collection("user").document(userId)
        dataUser.addSnapshotListener { snapshot, error ->

            error?.let {
                Log.d(TAG, it.message.toString())
                return@addSnapshotListener
            }

            snapshot?.let {
                val nama = it["nama"]
                val fotoProfil = it["fotoProfil"]

                binding.tvNama.text = nama.toString()
                Glide.with(this)
                    .load(fotoProfil)
                    .centerCrop()
                    .into(binding.ivPhoto)
            }
        }
    }

    companion object {
        const val TAG = "DashboardGuruActivity"
    }

}