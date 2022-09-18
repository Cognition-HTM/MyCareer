package com.sparklead.mycareer.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class User (
    var id: String = " ",
    val name : String = " ",
    val email :String =" ",
    val userType :String =" ",
    val image: String =" ",
    val phone :Long = 0L,
    val currentClass : String = " ",
    val stream :String= " ",
    val interests : String = " ",
    val gender: String = " ",
    val profileCompleted:Int = 0,
    val linkedin :String = " ",
    val resume : String = " ",
    val fieldExpert :String = " ",
    val about : String = " ",
    val login : Int = 0,
    val popUp : Int =0,
    val rating : String = " ",
    val result : String = " "
):Parcelable