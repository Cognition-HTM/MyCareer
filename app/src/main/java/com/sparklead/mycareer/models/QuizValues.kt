package com.sparklead.mycareer.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class QuizValues(
    val option : String = " ",
    val index : Int = -1
):Parcelable
