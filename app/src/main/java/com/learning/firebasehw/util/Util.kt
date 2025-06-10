package com.learning.firebasehw.util

import android.content.Context
import android.widget.Toast
import com.google.android.gms.common.api.Status
import com.learning.firebasehw.model.StudentModel

const val STUDENT_REF = "students"

// Operation types for the error message
const val OP_ADD = "add"
const val OP_UPDATE = "update"
const val OP_DELETE = "delete"
const val OP_LOAD = "load"

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

//Toast Message for Extension Function
fun Context.showToast(status: String){
    Toast.makeText(this, status, Toast.LENGTH_SHORT).show()
}