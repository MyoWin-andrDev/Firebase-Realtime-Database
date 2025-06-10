package com.learning.firebasehw.util


object Constants {
    const val STUDENT_DATA = "STUDENT_DATA"
    const val GENDER_MALE = 1
    const val GENDER_FEMALE = 0
    const val GENDER_UNSELECTED = -1

    object Dialog {
        const val DELETE_TITLE = "Delete Confirmation"
        const val DELETE_MESSAGE = "Are you sure you want to delete %s?"
        const val DELETE_POSITIVE = "Delete"
        const val DELETE_NEGATIVE = "Cancel"
    }

    object Validation {
        const val NAME = "Name"
        const val GRADE = "Grade"
        const val ROOM_NO = "Room No"
        const val FATHER_NAME = "Father's Name"
    }

    object Messages {
        const val RECORD_ADDED = "%s's Record Added"
        const val RECORD_UPDATED = "%s's Record Updated"
        const val ADD_FAILED = "Could not add student"
        const val UPDATE_FAILED = "Could not update student"
        const val DELETE_SUCCESS = "%s's records deleted successfully"
        const val GENERAL_ERROR = "Something went wrong"
    }

    object Titles {
        const val ADD_STUDENT = "Add Student"
        const val EDIT_STUDENT = "Edit Student"
    }
}