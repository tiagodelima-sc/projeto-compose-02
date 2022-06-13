package com.example.projetocompose02.views.subject

import androidx.lifecycle.*
import com.example.projetocompose02.data.daos.SubjectDao
import com.example.projetocompose02.data.models.Subject
import com.example.projetocompose02.data.models.relations.SubjectWithStudents
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class SubjectViewModel(private val dao: SubjectDao): ViewModel() {

    val allSubjects: LiveData<List<Subject>> = dao.getSubject().asLiveData()

    val allSubjectsWithStudents: LiveData<List<SubjectWithStudents>> = dao.getSubjectWithStudents().asLiveData()

    fun insert(subject: Subject){
        viewModelScope.launch {
            dao.insert(subject)
        }
    }

    fun update(subject: Subject){
        viewModelScope.launch {
            dao.update(subject)
        }
    }

    fun delete(subject: Subject){
        viewModelScope.launch {
            dao.delete(subject)
        }
    }

    fun getSubject(id: Int): Subject{
        allSubjects.value?.forEach{
            if(id == it.id){
                return it
            }
        }

        return Subject(
            -1,
            "",
            ""
        )
    }

    fun getLastIdSubject(): Int{
        return allSubjects.value?.get(allSubjects.value?.size?:0)?.id?:0
    }

    class SubjectViewModelFactory(private val dao: SubjectDao) : ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(SubjectViewModel::class.java))
                return SubjectViewModel(dao) as T
            throw IllegalArgumentException("Unknown ViewModel Class")
        }
    }

}