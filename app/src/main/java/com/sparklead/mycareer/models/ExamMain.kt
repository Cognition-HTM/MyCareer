package com.sparklead.mycareer.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ExamMain(
    val item : ArrayList<Exams> = ArrayList()
):Parcelable
