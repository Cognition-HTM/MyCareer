package com.sparklead.mycareer.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Sessions (
    val id: String = " ",
    val item: ArrayList<PreviousSessions> = ArrayList()
):Parcelable