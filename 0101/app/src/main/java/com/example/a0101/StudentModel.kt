package com.example.a0101
data class StudentModel(val studentName: String, val studentId: String) {
    override fun toString(): String {
        return studentName + " - " + studentId;
    }
}