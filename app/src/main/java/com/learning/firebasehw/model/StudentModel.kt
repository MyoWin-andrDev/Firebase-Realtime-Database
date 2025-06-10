package com.learning.firebasehw.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StudentModel(
    val studentId : String = "",
    val studentName : String = "",
    val studentGrade : String = "",
    val studentRoom : String = "",
    val studentGender : Int = 0,
    val studentFatherName : String = ""
) : Parcelable
