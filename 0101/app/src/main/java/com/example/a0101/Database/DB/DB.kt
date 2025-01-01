package com.example.a0101.Database.DB

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.a0101.Database.DAO.StudentDAO
import com.example.a0101.Database.Model.Student_Model

@Database(entities = [Student_Model::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun studentDao(): StudentDAO
}