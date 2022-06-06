package com.example.projetocompose02.data.models.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.projetocompose02.data.models.School
import com.example.projetocompose02.data.models.Subject

data class SchoolWithSubjectsWithStudents(
    @Embedded val school: School,
    @Relation(
        entity = Subject::class,
        parentColumn = "studentId",
        entityColumn = "subjectId"
    )
    val subject01: SubjectWithStudents,
    @Relation(
        entity = Subject::class,
        parentColumn = "studentId",
        entityColumn = "subjectId"
    )
    val subject02: SubjectWithStudents,
) {
}