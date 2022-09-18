package com.sparklead.mycareer.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BranchItem(
    val no : String = " ",
    val details : String = " ",
    val branch1 : Info = Info(),
    val branch2 : Info = Info(),
    val branch3 : Info = Info(),
    val branch4 : Info = Info(),
    val branch5 : Info = Info(),
    val branch6 : Info = Info(),
    val branch7 : Info = Info(),
    val branch8 : Info = Info(),
    val branch9 : Info = Info(),
    val branch10 : Info = Info(),
    val branch11 : Info = Info(),
    val branch12 : Info = Info(),
    val branch13 : Info = Info(),
    val branch14 : Info = Info(),
    val branch15 : Info = Info(),
) : Parcelable
