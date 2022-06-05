package com.example.projetocompose02.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student")
data class Student(
    @PrimaryKey(autoGenerate = true)
    val studentId: Int = 0,
    val semester: Int,
    val age: Int,
    val schoolName: String
) {
}