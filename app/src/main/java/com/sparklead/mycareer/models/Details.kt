package com.sparklead.mycareer.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Details(
    val name : String = " ",
    val des : String = " ",
    val future: String = " "
):Parcelable
