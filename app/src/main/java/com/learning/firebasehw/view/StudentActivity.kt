package com.learning.firebasehw.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.learning.firebasehw.R
import com.learning.firebasehw.databinding.ActivityStudentBinding

class StudentActivity : AppCompatActivity() {
    private lateinit var binding : ActivityStudentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}