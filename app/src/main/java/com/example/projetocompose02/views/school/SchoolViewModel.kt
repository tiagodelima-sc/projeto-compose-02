package com.example.projetocompose02.views.school

import androidx.lifecycle.*
import com.example.projetocompose02.data.daos.SchoolDao
import com.example.projetocompose02.data.models.School
import com.example.projetocompose02.data.models.relations.SchoolWithStudents
import com.example.projetocompose02.data.models.relations.SchoolWithSubjectsWithStudents
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class SchoolViewModel(private val dao: SchoolDao): ViewModel() {

    val allSchool: LiveData<List<School>> = dao.getSchool().asLiveData()
    val allSchoolWithStudents: LiveData<List<SchoolWithSubjectsWithStudents>> = dao.getSchoolWithSubjectsWithStudents().asLiveData()

    fun insert(school: School){
        viewModelScope.launch {
            dao.insert(school)
        }
    }

    fun update(school: School){
        viewModelScope.launch {
            dao.update(school)
        }
    }

    fun delete(school: School){
        viewModelScope.launch {
            dao.delete(school)
        }
    }

    class SchoolViewModelFactory(private val dao: SchoolDao) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SchoolViewModel::class.java))
                return SchoolViewModel(dao) as T
            throw IllegalArgumentException("Unknown ViewModel Class")
        }
    }



}