package com.sparklead.mycareer.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Explore(
    val image : String = " ",
    val title : String = " ",
    val des : String = " ",
    val type: ArrayList<Details> = ArrayList<Details>()

):Parcelable
