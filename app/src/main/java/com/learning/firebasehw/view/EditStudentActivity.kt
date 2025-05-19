package com.learning.firebasehw.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.learning.firebasehw.R
import com.learning.firebasehw.databinding.ActivityEditStudentBinding

class EditStudentActivity : AppCompatActivity() {
    private lateinit var binding : ActivityEditStudentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}