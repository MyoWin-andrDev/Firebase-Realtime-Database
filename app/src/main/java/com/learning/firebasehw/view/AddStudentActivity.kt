package com.learning.firebasehw.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.learning.firebasehw.databinding.ActivityAddStudentBinding
import com.learning.firebasehw.model.StudentModel
import com.learning.firebasehw.util.Constants
import com.learning.firebasehw.util.showToast
import com.learning.firebasehw.util.validateNotEmpty
import com.learning.firebasehw.viewmodel.StudentViewModel
import kotlinx.coroutines.launch

class AddStudentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddStudentBinding
    private val viewModel: StudentViewModel by viewModels()
    private var student: StudentModel? = null
    private var latestID: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)


        loadStudentData()
        loadStudentId()
        setupViews()
        setupObservers()
    }

    private fun loadStudentData() {
        student = intent.getParcelableExtra<StudentModel>(Constants.STUDENT_DATA)
        Log.d("student", student.toString())
        binding.tbHome.title = if (student != null) Constants.Titles.EDIT_STUDENT else Constants.Titles.ADD_STUDENT
        binding.tbHome.setNavigationOnClickListener { finish() }
        student?.let { populateFields(it) }
    }

    private fun loadStudentId(){
        lifecycleScope.launch {
            viewModel.getLatestId()
        }
    }

    private fun setupViews() {
        binding.btSave.setOnClickListener {
            if (validateFields()) {
                saveStudent()
            }
        }
    }

    private fun setupObservers() {
        viewModel.operationStatus.observe(this) { either ->
            either?.fold(
                ifLeft = { errorMsg -> showToast(errorMsg)},
                ifRight = {
                    successMsg -> showToast(successMsg)
                    finish()
                }
            )
        }
        viewModel.getLatestId.observe(this){ either ->
            either?.fold(
                ifLeft = { errorMsg -> showToast(errorMsg) },
                ifRight = {
                    studentId -> latestID = studentId
                    Log.d("ID", latestID.toString())
                }
            )
        }
    }

    private fun populateFields(student: StudentModel) {
        with(binding) {
            etName.setText(student.studentName)
            etGrade.setText(student.studentGrade)
            etRoomNo.setText(student.studentRoom)
            etFatherName.setText(student.studentFatherName)
            when (student.studentGender) {
                Constants.GENDER_MALE -> rbMale.isChecked = true
                Constants.GENDER_FEMALE -> rbFemale.isChecked = true
            }
        }
    }

    private fun validateFields(): Boolean {
        with(binding) {
            val isNameValid = etName.validateNotEmpty(Constants.Validation.NAME)
            val isGradeValid = etGrade.validateNotEmpty(Constants.Validation.GRADE)
            val isRoomValid = etRoomNo.validateNotEmpty(Constants.Validation.ROOM_NO)
            val isFatherNameValid = etFatherName.validateNotEmpty(Constants.Validation.FATHER_NAME)

            val isGenderValid = if (rgGender.checkedRadioButtonId == -1) {
                tvGenderError.visibility = View.VISIBLE
                false
            } else {
                tvGenderError.visibility = View.GONE
                true
            }

            return isNameValid && isGradeValid && isRoomValid && isFatherNameValid && isGenderValid
        }
    }

    private fun saveStudent() {
        lifecycleScope.launch {
            val studentData = createStudentFromInput()
            if (student == null) {
                viewModel.addStudent(studentData)
            } else {
                viewModel.updateStudent(studentData.copy(studentId = student!!.studentId))
            }
        }
    }

    private fun createStudentFromInput(): StudentModel = with(binding) {
        return StudentModel(
            studentName = etName.text.toString().trim(),
            studentGrade =  etGrade.text.toString().trim(),
            studentRoom = etRoomNo.text.toString().trim(),
            studentGender = getSelectedGender(),
            studentFatherName = etFatherName.text.toString().trim(),
            studentId = latestID
        )
    }

    private fun getSelectedGender(): Int {
        return when (binding.rgGender.checkedRadioButtonId) {
            binding.rbMale.id -> Constants.GENDER_MALE
            binding.rbFemale.id -> Constants.GENDER_FEMALE
            else -> Constants.GENDER_UNSELECTED
        }
    }
}