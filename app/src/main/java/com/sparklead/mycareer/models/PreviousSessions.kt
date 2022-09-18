package com.sparklead.mycareer.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PreviousSessions (
    val title: String = " ",
    val description :String = " ",
    val session_datetime : Long = 0L
):Parcelable