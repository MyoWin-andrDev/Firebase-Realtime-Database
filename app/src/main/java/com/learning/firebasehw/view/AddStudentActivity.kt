package com.learning.firebasehw.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.learning.firebasehw.databinding.ActivityEditStudentBinding
import com.learning.firebasehw.model.StudentModel
import com.learning.firebasehw.util.showStatusToast
import com.learning.firebasehw.viewmodel.StudentViewModel
import kotlinx.coroutines.launch

class AddStudentActivity : AppCompatActivity() {
    private lateinit var binding : ActivityEditStudentBinding
    private val viewModel : StudentViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnSave.setOnClickListener {
            lifecycleScope.launch {
                viewModel.addStudent(
                    StudentModel(
                        studentId = "A00002",
                        studentName = "Myo Win",
                        studentGrade = "A+",
                        studentRoom = "Computer Science",
                        studentGender = 1,
                        studentFatherName = "Thomas"
                    )
                )
                viewModel.operationStatus.observe(this@AddStudentActivity) {
                    showStatusToast(it.toString())
                }
            }
        }
    }
}