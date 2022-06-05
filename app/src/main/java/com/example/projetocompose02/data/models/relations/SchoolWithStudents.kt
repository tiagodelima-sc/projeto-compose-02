package com.example.projetocompose02.data.models.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.projetocompose02.data.models.School
import com.example.projetocompose02.data.models.Student

data class SchoolWithStudents(
    @Embedded val school: School,
    @Relation(
        parentColumn = "schoolId",
        entityColumn = "studentsSchoolId"
    )
    val students: List<Student>
) {
}