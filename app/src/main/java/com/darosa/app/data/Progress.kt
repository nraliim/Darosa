package com.darosa.app.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Progress(
    val title: String? = null,
    val query: String? = null,
    val user: User? = null
) : Parcelable
