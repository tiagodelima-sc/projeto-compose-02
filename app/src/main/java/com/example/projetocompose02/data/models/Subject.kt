package com.example.projetocompose02.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subject")
data class Subject(
    @PrimaryKey(autoGenerate = true)
    val subjectId: Int = 0,
    val subjectName: String
) {
}