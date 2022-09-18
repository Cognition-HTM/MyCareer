package com.sparklead.mycareer.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Exams(
    val title: String = " ",
    val image : String = " ",
    val college : String = " ",
    val career : String = " ",
    val pre : String = " ",
    val link :String = " "
):Parcelable
