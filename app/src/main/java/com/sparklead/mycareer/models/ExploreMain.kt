package com.sparklead.mycareer.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ExploreMain(
    val item: ArrayList<Explore> = ArrayList()
):Parcelable
