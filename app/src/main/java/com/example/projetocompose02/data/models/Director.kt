package com.example.projetocompose02.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "director")
data class Director(
    @PrimaryKey(autoGenerate = true)
    val directorName: String,
    val schoolName: String
) {
}