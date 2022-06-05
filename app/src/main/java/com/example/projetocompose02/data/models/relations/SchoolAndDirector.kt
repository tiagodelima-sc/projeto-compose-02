package com.example.projetocompose02.data.models.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.projetocompose02.data.models.Director
import com.example.projetocompose02.data.models.School

data class SchoolAndDirector(
    @Embedded val school: School,
    @Relation(
        parentColumn = "schoolName",
        entityColumn = "schoolName"
    )
    val director: Director

) {
}