package com.sparklead.mycareer.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Blogs(
    val id: String = " ",
    val item: ArrayList<PreviousBlogs> = ArrayList()
):Parcelable
