package com.sparklead.mycareer.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Favourite(
    val image: String = " ",
    val title : String = " ",
    val des : String = " ",
    val skill : String = " "
):Parcelable
