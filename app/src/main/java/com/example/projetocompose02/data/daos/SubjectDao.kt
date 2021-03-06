package com.example.projetocompose02.data.daos

import androidx.room.*
import com.example.projetocompose02.data.models.Subject
import com.example.projetocompose02.data.models.relations.SubjectWithStudents
import kotlinx.coroutines.flow.Flow

@Dao
interface SubjectDao {

    @Insert(onConflict=OnConflictStrategy.REPLACE)
    suspend fun insert(subject: Subject)

    @Update
    suspend fun update(subject: Subject)

    @Delete
    suspend fun delete(subject: Subject)

    @Query("SELECT * FROM subject")
    fun getSubject(): Flow<List<Subject>>

    @Transaction
    @Query("SELECT * FROM subject")
    fun getSubjectWithStudents(): Flow<List<SubjectWithStudents>>

}