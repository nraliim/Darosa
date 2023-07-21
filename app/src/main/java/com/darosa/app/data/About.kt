package com.darosa.app.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class About(
    val title: String,
    val photoAbout: Int,
    val description: String
) : Parcelable
