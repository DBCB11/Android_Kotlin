package com.example.a0101.Database.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student_table")
data class Student_Model(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val studentId: String
)