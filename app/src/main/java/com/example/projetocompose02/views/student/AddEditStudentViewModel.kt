package com.example.projetocompose02.views.student

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projetocompose02.data.models.Student

class AddEditStudentViewModel : ViewModel() {

    private val _id: MutableLiveData<Int> = MutableLiveData()
    val name: MutableLiveData<String> = MutableLiveData()
    val semester: MutableLiveData<String> = MutableLiveData()
    val age: MutableLiveData<String> = MutableLiveData()
    val schoolName: MutableLiveData<String> = MutableLiveData()

    fun insertStudent(
        onInsertStudent: (Student) -> Unit
    ){
        val newStudent = Student(
            _id.value?: return,
            name.value?: return,
            semester.value?: return,
            age.value?: return,
            schoolName.value?: return
        )
        onInsertStudent(newStudent)
        var idTemporario: Int = _id.value ?: return
        idTemporario++
        _id.value = idTemporario
        name.value = ""
        semester.value = ""
        age.value = ""
        schoolName.value = ""
    }

    fun updateStudent(
        id: Int,
        onUpdateStudent: (Student) -> Unit
    ){
        val student = Student(
            id,
            name.value?: return,
            semester.value?: return,
            age.value?:return,
            schoolName.value?:return,
        )
        onUpdateStudent(student)
    }

    fun setIdStudent(index: Int){
        _id.value = index
    }
}

