package com.learning.firebasehw.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.learning.firebasehw.model.StudentModel
import com.learning.firebasehw.repository.StudentRepository

class StudentViewModel : ViewModel(){

    private val studentRepository = StudentRepository()

    private val _studentList = MutableLiveData<List<StudentModel>>()
    val studentList : LiveData<List<StudentModel>> = _studentList

    private val _operationStatus = MutableLiveData<String?>()
    val operationStatus : LiveData<String?> = _operationStatus

    suspend fun addStudent(student : StudentModel){
        clearData()
        val result = studentRepository.addStudent(student)
        result.onSuccess {
            _operationStatus.postValue(it)
        }
        result.onFailure {
            _operationStatus.postValue(it.toString())
        }
    }
    suspend fun updateStudent(student : StudentModel){
        clearData()
        val result = studentRepository.updateStudent(student)
        result.onSuccess {
            _operationStatus.postValue(it)
        }
        result.onFailure {
            _operationStatus.postValue(it.toString())
        }
    }
    fun clearData(){
        _operationStatus.postValue(null)
    }
}