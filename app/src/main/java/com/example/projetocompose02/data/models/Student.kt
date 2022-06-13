package com.example.projetocompose02.data.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey



@Entity(tableName = "student",)
data class Student(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val semester: Int,
    val age: Int,
    val schoolName: String? = null
) {
}

/* foreignKeys = [
     ForeignKey(
         entity = School::class,
         parentColumns = arrayOf("id"),
         childColumns = arrayOf("schoolName"),
         onDelete = ForeignKey.SET_NULL,
         onUpdate = ForeignKey.CASCADE
     )
 ]*/