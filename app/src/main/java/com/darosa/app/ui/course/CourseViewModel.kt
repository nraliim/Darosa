package com.darosa.app.ui.course

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.darosa.app.data.Pages
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class CourseViewModel : ViewModel() {

    private val userId = FirebaseAuth.getInstance().currentUser!!.uid
    private val db = FirebaseFirestore.getInstance()
    private val _allPages = MutableLiveData<List<Pages>>()
    private val allPages: LiveData<List<Pages>> get() = _allPages

    fun getCourseData(query: String) : LiveData<List<Pages>> {
        db.collection(query).document(userId)
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
//                                page["materi"] as? String,
//                                page["audio"] as? String,
                                page["code"] as? Long,
                                page["isChecked"] as? Boolean
                            )
                        }
//                        pagesList.addAll(pages)
                        _allPages.value = pages
                    }
//                    _allPages.value = pagesList
                }
            }
        return allPages
    }

    companion object {
        const val TAG = "CourseViewModel"
    }

}