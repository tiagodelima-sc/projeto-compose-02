package com.example.projetocompose02.data.daos

import androidx.room.*
import com.example.projetocompose02.data.models.School
import com.example.projetocompose02.data.models.relations.*
import kotlinx.coroutines.flow.Flow

@Dao
interface SchoolDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(school: School)

    @Update
    suspend fun update(school: School)

    @Delete
    suspend fun delete(school: School)

    @Query("SELECT * FROM school")
    fun getSchool(): Flow<List<School>>

    @Transaction
    @Query("SELECT * FROM student")
    fun getSchoolWithStudents(): Flow<List<SchoolWithStudents>>

}