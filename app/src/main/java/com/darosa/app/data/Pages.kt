package com.darosa.app.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class Jilid(
    val pages: List<Pages> ?= null
)

@Parcelize
data class Pages(
    val halaman: String? = null,
//    val materi: String? = null,
//    val audio: String? = null,
    val code: Long? = null,
    @field:JvmField
    val isChecked: Boolean? = false
) : Parcelable
