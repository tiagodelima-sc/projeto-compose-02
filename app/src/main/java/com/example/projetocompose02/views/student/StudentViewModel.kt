package com.example.projetocompose02.views.student

import androidx.lifecycle.*
import com.example.projetocompose02.data.daos.StudentDao
import com.example.projetocompose02.data.models.Student
import com.example.projetocompose02.data.models.relations.StudentWithSubjects
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class StudentViewModel(private val dao: StudentDao) : ViewModel() {

    val allStudents: LiveData<List<Student>> = dao.getStudents().asLiveData()

    val allStudentsWithSubjects: LiveData<List<StudentWithSubjects>> = dao.getStudentWithSubjects().asLiveData()

    fun insert(student: Student) {
        viewModelScope.launch {
            dao.insert(student)
        }
    }

    fun update(student: Student) {
        viewModelScope.launch {
            dao.update(student)
        }
    }

    fun delete(student: Student) {
        viewModelScope.launch {
            dao.delete(student)
        }
    }

    fun getStudent(id: Int): Student {
        allStudents.value?.forEach {
            if (id == it.id) {
                return it
            }
        }

        return Student(
            -1,
            "",
            0,
            0,
            ""
        )
    }

    fun getLastIdStudent(): Int{
        return allStudents.value?.get(allStudents.value?.size?:0)?.id?:0
    }

    class StudentViewModelFactory(private val dao: StudentDao) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(StudentViewModel::class.java))
                return StudentViewModel(dao) as T
            throw IllegalArgumentException("Unknown ViewModel Class")
        }
    }

}