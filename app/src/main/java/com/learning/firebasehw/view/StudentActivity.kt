package com.learning.firebasehw.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
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
            Log.d("Data", it.toString())
        }
    }
}