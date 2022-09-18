package com.sparklead.mycareer.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PreviousBlogs(
    val title :String = " ",
    val description :String = " "
):Parcelable
