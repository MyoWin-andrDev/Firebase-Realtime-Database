package com.learning.firebasehw.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.learning.firebasehw.R
import com.learning.firebasehw.adapter.StudentAdapter
import com.learning.firebasehw.databinding.ActivityStudentBinding
import com.learning.firebasehw.viewmodel.StudentViewModel
import kotlinx.coroutines.launch

class StudentActivity : AppCompatActivity() {
    private val viewModel : StudentViewModel by viewModels()
    private lateinit var binding : ActivityStudentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fbAddStudent.setOnClickListener {
            startActivity(Intent(this@StudentActivity, EditStudentActivity::class.java))
        }
        setupRecyclerView()
        loadStudent()
        observeViewModel()
    }

    private fun setupRecyclerView(){
        binding.rvStudent.adapter = StudentAdapter(
            studentList = emptyList(),
            onDeleteClick = {},
            onItemClick = {}
        )
    }
    private fun loadStudent(){
        lifecycleScope.launch {
            viewModel.getAllStudent()
        }
    }
    private fun observeViewModel(){
        viewModel.studentList.observe(this) {
            binding.rvStudent.adapter = StudentAdapter(
                studentList = it,
                onDeleteClick = {},
                onItemClick = {}
            )
        }
    }
}