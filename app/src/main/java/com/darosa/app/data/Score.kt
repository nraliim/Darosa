package com.darosa.app.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Score(
  val id: String? = null,
  val nama: String? = null,
  val jilid0: Int? = null,
  val jilid1: Int? = null,
  val jilid2: Int? = null,
  val jilid3: Int? = null
) : Parcelable
