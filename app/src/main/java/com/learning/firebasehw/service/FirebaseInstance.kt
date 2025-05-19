package com.learning.firebasehw.service

import com.google.firebase.Firebase
import com.google.firebase.database.database
import com.learning.firebasehw.util.STUDENT_REF

class FirebaseInstance {
    private val database = Firebase.database
    val studentRef = database.getReference(STUDENT_REF)
}