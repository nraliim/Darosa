package com.darosa.app.ui.progress

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.darosa.app.data.Pages
import com.darosa.app.data.Progress
import com.darosa.app.databinding.ActivityDetailProgressBinding
import com.darosa.app.ui.course.CourseAdapter
import com.google.firebase.firestore.FirebaseFirestore

@Suppress("DEPRECATION")
class DetailProgressActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailProgressBinding
    private lateinit var adapter: CourseAdapter
    private lateinit var db: FirebaseFirestore

    companion object {
        const val TAG = "DetailProgress"
        const val EXTRA_PROGRESS = "extra_progress"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailProgressBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = FirebaseFirestore.getInstance()
        adapter = CourseAdapter()

        val userProgress = intent.getParcelableExtra<Progress>(EXTRA_PROGRESS) as Progress
        initializeData(userProgress)

    }

    private fun initializeData(progress: Progress) {
        binding.apply {
            tvJudul.text = progress.title
            rvPages.layoutManager = LinearLayoutManager(applicationContext)
            rvPages.adapter = adapter
        }

        db.collection(progress.query.toString()).document(progress.user?.id.toString())
            .addSnapshotListener { value, error ->
                error?.let {
                    Log.d(TAG, it.message.toString())
                    return@addSnapshotListener
                }
                value?.let {
//                    val pagesList = mutableListOf<Pages>()
                    val pagesItem = it.get("pages") as? List<Map<*, *>>?
                    if (pagesItem != null) {
                        val pages = pagesItem.map { page ->
                            Pages(
                                page["halaman"] as? String,
                                page["code"] as? Long,
                                page["isChecked"] as? Boolean
                            )
                        }
                        adapter.setPages(pages)
                    }
                }
            }
    }

}