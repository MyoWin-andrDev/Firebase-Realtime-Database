package com.learning.firebasehw.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import arrow.core.Either
import com.learning.firebasehw.model.StudentModel
import com.learning.firebasehw.repository.StudentRepository

class StudentViewModel : ViewModel(){

    private val studentRepository = StudentRepository()

    private val _studentList = MutableLiveData<Either<String, List<StudentModel>>>()
    val studentList : LiveData<Either<String, List<StudentModel>>> = _studentList

    private val _operationStatus = MutableLiveData<Either<String, String>>()
    val operationStatus : LiveData<Either<String, String>> = _operationStatus

    private val _getLatestId = MutableLiveData<Either<String, Int>>()
    val getLatestId : LiveData<Either<String, Int>> = _getLatestId

    suspend fun addStudent(student : StudentModel){
        studentRepository.addStudent(student)
            .onSuccess { success ->
            _operationStatus.postValue(Either.Right(success))
            }
            .onFailure { throwable ->
            _operationStatus.postValue(Either.Left(throwable.toString()))
            }
    }

    suspend fun updateStudent(student : StudentModel){
        studentRepository.updateStudent(student)
            .onSuccess { success ->
            _operationStatus.postValue(Either.Right(success))
            }
            .onFailure { throwable ->
            _operationStatus.postValue(Either.Left(throwable.toString()))
            }
    }

    suspend fun deleteStudent(student: StudentModel){
        studentRepository.deleteStudent(student)
            .onSuccess { success ->
                _operationStatus.postValue(Either.Right(success))
            }
            .onFailure { throwable ->
                _operationStatus.postValue(Either.Left(throwable.toString()))
            }

    }
    suspend fun getAllStudent(){
        studentRepository.getAllStudents()
            .onSuccess { studentList ->
                _studentList.postValue(Either.Right(studentList) )
            }
            .onFailure { throwable ->
                _studentList.postValue(Either.Left(throwable.toString()))

            }
    }
    suspend fun getLatestId(){
        studentRepository.getLatestStudentId()
            .onSuccess { id->
                _getLatestId.postValue(Either.Right(id))
            }
            .onFailure { throwable ->
                _getLatestId.postValue(Either.Left(throwable.toString()))
            }
    }
}