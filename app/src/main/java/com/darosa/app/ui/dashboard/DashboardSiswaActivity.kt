package com.darosa.app.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.darosa.app.R
import com.darosa.app.data.Course
import com.darosa.app.databinding.ActivityDashboardSiswaBinding
import com.darosa.app.ui.course.CourseActivity
import com.darosa.app.ui.profil.ProfilActivity
import com.darosa.app.utils.loadImageUrl
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class DashboardSiswaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardSiswaBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var networkConnection: NetworkConnectionLiveData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardSiswaBinding.inflate(layoutInflater)
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
        let {
            binding.ivPhoto.setOnClickListener {
                startActivity(Intent(this, ProfilActivity::class.java))
            }
            binding.cvJilidPemula.setOnClickListener {
                val jilidPemula = Course(
                    getString(R.string.yanbua_jilid_pemula),
                    R.drawable.yanbua_pemula,
                    getString(R.string.deskripsi_jilid_pemula),
                    0
                )
                val intent = Intent(this, CourseActivity::class.java)
                intent.putExtra(CourseActivity.EXTRA_FRAGMENT_TYPE, jilidPemula)
                startActivity(intent)
            }
            binding.cvJilid1.setOnClickListener {
                val jilidSatu = Course(
                    getString(R.string.yanbua_jilid_1),
                    R.drawable.yanbua_1,
                    getString(R.string.deskripsi_jilid_1),
                    1
                )
                val intent = Intent(this, CourseActivity::class.java)
                intent.putExtra(CourseActivity.EXTRA_FRAGMENT_TYPE, jilidSatu)
                startActivity(intent)
            }
            binding.cvJilid2.setOnClickListener {
                val jilidDua = Course(
                    getString(R.string.yanbua_jilid_2),
                    R.drawable.yanbua_2,
                    getString(R.string.deskripsi_jilid_2),
                    2
                )
                val intent = Intent(this, CourseActivity::class.java)
                intent.putExtra(CourseActivity.EXTRA_FRAGMENT_TYPE, jilidDua)
                startActivity(intent)
            }
            binding.cvJilid3.setOnClickListener {
                val jilidTiga = Course(
                    getString(R.string.yanbua_jilid_3),
                    R.drawable.yanbua_3,
                    getString(R.string.deskripsi_jilid_3),
                    3
                )
                val intent = Intent(this, CourseActivity::class.java)
                intent.putExtra(CourseActivity.EXTRA_FRAGMENT_TYPE, jilidTiga)
                startActivity(intent)
            }
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
                val progress = it["progress"] as Map<*, *>
                val jilid0 = progress["jilid0"] as Double
                val jilid1 = progress["jilid1"] as Double
                val jilid2 = progress["jilid2"] as Double
                val jilid3 = progress["jilid3"] as Double

                binding.apply {
                    tvNama.text = nama.toString()
                    ivPhoto.loadImageUrl(fotoProfil.toString())

                    // Jilid Pemula
                    tvProgress.text = jilid0.toInt().toString()
                    progressIndicatorBar.progress = jilid0.toInt()

                    // Jilid 1
                    tvProgress1.text = jilid1.toInt().toString()
                    progressIndicatorBar1.progress = jilid1.toInt()

                    // Jilid 2
                    tvProgress2.text = jilid2.toInt().toString()
                    progressIndicatorBar2.progress = jilid2.toInt()

                    // Jilid 3
                    tvProgress3.text = jilid3.toInt().toString()
                    progressIndicatorBar3.progress = jilid3.toInt()
                }
            }
        }

    }

    companion object {
        const val TAG = "DashboardSiswaActivity"
    }
}