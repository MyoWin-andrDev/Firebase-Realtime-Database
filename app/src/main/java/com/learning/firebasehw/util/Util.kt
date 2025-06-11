package com.learning.firebasehw.util

import android.content.Context
import android.widget.Toast
import com.google.android.gms.common.api.Status
import com.google.android.material.textfield.TextInputEditText
import com.learning.firebasehw.model.StudentModel
import java.util.concurrent.Executor

const val STUDENT_REF = "students"

// Extension functions for Result messages
fun StudentModel.addSuccessMessage() =
    "${this.studentName}'s records have been added successfully"

fun StudentModel.updateSuccessMessage() =
    "${this.studentName}'s records have been updated successfully"

fun StudentModel.deleteSuccessMessage() =
    "${this.studentName}'s records have been deleted successfully"

fun StudentModel.operationFailedMessage(operation: String, e: Exception) =
    "Failed to $operation ${this.studentName}'s record. ${e.message}"

fun loadOperationFailed(operation : String ,e : Exception) =
    "Failed to $operation Student Data. ${e.message}"

fun getIdFailed(operation: String, e : Exception) =
    "Failed to $operation student latest ID. ${e.message} "

//Toast Message for Extension Function
fun Context.showToast(status: String){
    Toast.makeText(this, status, Toast.LENGTH_SHORT).show()
}

fun TextInputEditText.validateNotEmpty(errorMessage: String): Boolean {
    return if (text.toString().trim().isEmpty()) {
        error = errorMessage
        false
    } else {
        error = null
        true
    }
}