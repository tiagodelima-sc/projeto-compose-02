package com.example.projetocompose02.data.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "school",
    foreignKeys = [
        ForeignKey(
            entity = Subject::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("schoolName1"),
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Subject::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("schoolName2"),
            onDelete = ForeignKey.CASCADE
        ),
    ]
)
data class School(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val schoolName1: String,
    val schoolName2: String
) {
}