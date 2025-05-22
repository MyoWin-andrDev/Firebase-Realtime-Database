package com.learning.firebasehw.repository

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.learning.firebasehw.model.StudentModel
import com.learning.firebasehw.service.FirebaseInstance
import com.learning.firebasehw.util.OP_ADD
import com.learning.firebasehw.util.OP_DELETE
import com.learning.firebasehw.util.OP_LOAD
import com.learning.firebasehw.util.OP_UPDATE
import com.learning.firebasehw.util.addSuccessMessage
import com.learning.firebasehw.util.deleteSuccessMessage
import com.learning.firebasehw.util.loadOperationFailed
import com.learning.firebasehw.util.operationFailedMessage
import com.learning.firebasehw.util.updateSuccessMessage
import kotlinx.coroutines.tasks.await

class StudentRepository {
    private val studentRef = FirebaseInstance().studentRef

    suspend fun addStudent(student : StudentModel) : Result<String> = try {
        studentRef.child(student.studentId.toString()).setValue(student).await()
        Result.success(student.addSuccessMessage())
    }catch (e : Exception){
        Result.failure<String>(Exception(student.operationFailedMessage(OP_ADD,e)))
    }

    suspend fun updateStudent(student: StudentModel) : Result<String> = try {
        studentRef.child(student.studentId.toString()).setValue(student).await()
        Result.success(student.updateSuccessMessage())
    }catch (e : Exception){
        Result.failure<String>(Exception(student.operationFailedMessage(OP_UPDATE,e)))
    }

    suspend fun deleteStudent(student: StudentModel) : Result<String> = try {
        studentRef.child(student.studentId.toString()).removeValue().await()
        Result.success(student.deleteSuccessMessage())
    }
    catch (e : Exception){
        Result.failure<String>(Exception(student.operationFailedMessage(OP_DELETE,e)))
    }

    suspend fun getAllStudents () : Result<List<StudentModel>> = try {
        val studentList = ArrayList<StudentModel>()
        studentRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach {
                    it.getValue(StudentModel::class.java)?.let{ studentList.add(it)}
                }
            }
            override fun onCancelled(error: DatabaseError) {}

        })
        Result.success(studentList)
    }
    catch (e : Exception){
        Result.failure(Exception(loadOperationFailed(OP_LOAD, e)))
    }
}