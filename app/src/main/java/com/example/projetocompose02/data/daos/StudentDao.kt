package com.example.projetocompose02.data.daos

import androidx.room.*
import com.example.projetocompose02.data.models.Student
import com.example.projetocompose02.data.models.relations.StudentWithSubjects
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(student: Student)

    @Update
    suspend fun update(student: Student)

    @Delete
    suspend fun delete(student: Student)

    @Query("SELECT * FROM student")
    fun getStudents(): Flow<List<Student>>

    @Transaction
    @Query("SELECT * FROM student")
    fun getStudentWithSubjects(): Flow<List<StudentWithSubjects>>


}