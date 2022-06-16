package com.example.projetocompose02.data.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.ForeignKey.RESTRICT
import androidx.room.PrimaryKey

@Entity(
    tableName = "school",
    foreignKeys = [
        ForeignKey(
            entity = Subject::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("id"),
            onDelete = RESTRICT,
            onUpdate = CASCADE
        )
    ]
)
data class School(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val schoolName1: String,
    val city: String,
    val state: String
) {
}

