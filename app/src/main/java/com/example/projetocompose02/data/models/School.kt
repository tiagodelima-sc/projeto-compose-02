package com.example.projetocompose02.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "school")
data class School(
    @PrimaryKey(autoGenerate = true)
    val schoolId: Int = 0,
    val schoolName: String,
    val address: String
) {
}