package com.darosa.app.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Course(
    val title: String,
    val image: Int,
    val deskripsi: String,
    val typeFragment: Int
) : Parcelable
