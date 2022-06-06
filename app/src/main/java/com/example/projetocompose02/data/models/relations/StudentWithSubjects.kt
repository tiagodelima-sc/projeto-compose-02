package com.example.projetocompose02.data.models.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.projetocompose02.data.models.Student
import com.example.projetocompose02.data.models.Subject

data class StudentWithSubjects(
    @Embedded val student: Student,
    @Relation(
        parentColumn = "schoolName",
        entityColumn = "id",
    )
    val subjects: List<Subject>
) {
}