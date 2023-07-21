package com.darosa.app.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val nama: String? = null,
    val email: String? = null,
    val gender: String? = null,
    val nomorHp: String? = null,
    val alamat: String? = null,
    val fotoProfil: String? = null,
    @field:JvmField
    val isGuru: Boolean? = false,
    val progress: Belajar? = null
) : Parcelable

@Parcelize
data class Belajar(
    val jilid0: Double? = 0.01,
    val jilid1: Double? = 0.01,
    val jilid2: Double? = 0.01,
    val jilid3: Double? = 0.01
) : Parcelable
