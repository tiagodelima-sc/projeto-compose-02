package com.example.projetocompose02.data.models.relations

import androidx.room.Entity

@Entity(primaryKeys = ["subjectId", "studentId"])
data class StudentSubjectCrossRef(
    val studentName: String,
    val subjectName: String
) {
}