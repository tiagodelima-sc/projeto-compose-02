package com.example.projetocompose02.views.subject

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projetocompose02.data.models.Subject

class AddEditSubjectViewModel : ViewModel() {

    private val _id: MutableLiveData<Int> = MutableLiveData()
    val name: MutableLiveData<String> = MutableLiveData()
    val nameCourse: MutableLiveData<String> = MutableLiveData()

    fun insertSubject(
        onInsertSubject: (Subject) -> Unit
    ){
        val newSubject = Subject(
            _id.value?: return,
            name.value?: return,
            nameCourse.value?: return,

        )
        onInsertSubject(newSubject)
        var idTemporario: Int = _id.value ?: return
        idTemporario++
        _id.value = idTemporario
        name.value = ""
        nameCourse.value = ""
    }

    fun updateSubject(
        id: Int,
        onUpdateSubject: (Subject) -> Unit
    ){
        val subject = Subject(
            id,
            name.value?: return,
            nameCourse.value?: return,

        )
        onUpdateSubject(subject)
    }

    fun setIdSubject(index: Int){
        _id.value = index
    }
}
