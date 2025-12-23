package com.example.studentmanagement

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Student(
    val id: String,
    var name: String,
    var mssv: String,
    var phone: String,
    var address: String
) : Parcelable
