package com.darosa.app.ui.course

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.darosa.app.R
import com.darosa.app.data.Course
import com.darosa.app.databinding.ActivityCourseBinding
import com.darosa.app.ui.detail.JilidDuaFragment
import com.darosa.app.ui.detail.JilidPemulaFragment
import com.darosa.app.ui.detail.JilidSatuFragment
import com.darosa.app.ui.detail.JilidTigaFragment

class CourseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCourseBinding

    companion object {
        const val EXTRA_FRAGMENT_TYPE = "fragment_type"
        const val FRAGMENT_PEMULA = 0
        const val FRAGMENT_SATU = 1
        const val FRAGMENT_DUA = 2
        const val FRAGMENT_TIGA = 3
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCourseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        @Suppress("DEPRECATION")
        val course = intent.getParcelableExtra<Course>(EXTRA_FRAGMENT_TYPE) as Course


        binding.apply {
            Glide.with(applicationContext)
                .load(course.image)
                .into(ivHeader)
            tvJudul.text = course.title
            tvDeskripsi.text = course.deskripsi

        }

        setActionBarTitle(course.title)

        when (course.typeFragment) {
            FRAGMENT_PEMULA -> {
                replaceFragment(JilidPemulaFragment())
            }

            FRAGMENT_SATU -> {
                replaceFragment(JilidSatuFragment())
            }

            FRAGMENT_DUA -> {
                replaceFragment(JilidDuaFragment())
            }

            FRAGMENT_TIGA -> {
                replaceFragment(JilidTigaFragment())
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        fragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    private fun setActionBarTitle(jilid: String) {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = jilid
        }
    }
}