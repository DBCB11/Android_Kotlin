package com.example.a0101.Database.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.a0101.Database.Model.Student_Model

@Dao
interface StudentDAO {
    @Insert
    suspend fun insert(studentModel: Student_Model)

    @Query("SELECT * FROM student_table")
    suspend fun getAllStudent(): List<Student_Model>
}