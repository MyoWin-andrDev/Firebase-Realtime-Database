package com.learning.firebasehw.repository


import com.google.firebase.Firebase
import com.google.firebase.database.database
import com.learning.firebasehw.model.StudentModel
import com.learning.firebasehw.util.Constants.Operation.OP_ADD
import com.learning.firebasehw.util.Constants.Operation.OP_DELETE
import com.learning.firebasehw.util.Constants.Operation.OP_GET
import com.learning.firebasehw.util.Constants.Operation.OP_LOAD
import com.learning.firebasehw.util.Constants.Operation.OP_UPDATE
import com.learning.firebasehw.util.STUDENT_REF
import com.learning.firebasehw.util.addSuccessMessage
import com.learning.firebasehw.util.deleteSuccessMessage
import com.learning.firebasehw.util.getIdFailed
import com.learning.firebasehw.util.loadOperationFailed
import com.learning.firebasehw.util.operationFailedMessage
import com.learning.firebasehw.util.updateSuccessMessage
import kotlinx.coroutines.tasks.await

class StudentRepository {

    companion object {
        private val database = Firebase.database
        val studentRef = database.getReference(STUDENT_REF)
    }

    suspend fun addStudent(student: StudentModel): Result<String> = try {
        studentRef.child(student.studentId.toString()).setValue(student).await()
        Result.success(student.addSuccessMessage())
    } catch (e: Exception) {
        Result.failure<String>(Exception(student.operationFailedMessage(OP_ADD, e)))
    }

    suspend fun updateStudent(student: StudentModel): Result<String> = try {
        studentRef.child(student.studentId.toString()).setValue(student).await()
        Result.success(student.updateSuccessMessage())
    } catch (e: Exception) {
        Result.failure<String>(Exception(student.operationFailedMessage(OP_UPDATE, e)))
    }

    suspend fun deleteStudent(student: StudentModel): Result<String> = try {
        studentRef.child(student.studentId.toString()).removeValue().await()
        Result.success(student.deleteSuccessMessage())
    } catch (e: Exception) {
        Result.failure<String>(Exception(student.operationFailedMessage(OP_DELETE, e)))
    }

    suspend fun getAllStudents(): Result<List<StudentModel>> = try {
        Result.success(studentRef.get().await().children.mapNotNull {
            it.getValue(StudentModel::class.java)
        })
    } catch (e: Exception) {
        Result.failure(Exception(loadOperationFailed(OP_LOAD, e)))
    }

    suspend fun getLatestStudentId(): Result<Int> = try {
        val id = getAllStudents().getOrNull()?.maxByOrNull { it.studentId }?.studentId ?: 0
        Result.success(id.inc())
    } catch (e: Exception) {
        Result.failure(Exception(getIdFailed(OP_GET, e)))
    }
}