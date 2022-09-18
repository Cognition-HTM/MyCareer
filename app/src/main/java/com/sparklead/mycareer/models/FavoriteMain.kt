package com.sparklead.mycareer.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FavoriteMain(
//    val id : String,
    val item: ArrayList<Favourite> = ArrayList()
):Parcelable
