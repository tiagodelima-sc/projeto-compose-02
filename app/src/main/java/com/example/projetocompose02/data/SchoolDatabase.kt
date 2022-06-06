package com.example.projetocompose02.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.projetocompose02.data.daos.SchoolDao
import com.example.projetocompose02.data.daos.StudentDao
import com.example.projetocompose02.data.daos.SubjectDao
import com.example.projetocompose02.data.models.School
import com.example.projetocompose02.data.models.Student
import com.example.projetocompose02.data.models.Subject
import com.example.projetocompose02.data.models.relations.StudentSubjectCrossRef


@Database(
    entities = [
        School::class,
        Student::class,
        Subject::class,
        StudentSubjectCrossRef::class
    ],
    version = 1,
    exportSchema = false
)
abstract class SchoolDatabase : RoomDatabase() {

    abstract fun schoolDao(): SchoolDao
    abstract fun studentDao(): StudentDao
    abstract fun subjectDao(): SubjectDao

    companion object {

        @Volatile
        private var INSTANCE: SchoolDatabase? = null

        fun getInstance(context: Context): SchoolDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context,
                    SchoolDatabase::class.java,
                    "school_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}