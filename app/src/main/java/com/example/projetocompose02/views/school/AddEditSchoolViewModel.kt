package com.example.projetocompose02.views.school

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projetocompose02.data.models.School
import com.example.projetocompose02.data.models.Student

class AddEditSchoolViewModel : ViewModel() {

    private val _id: MutableLiveData<Int> = MutableLiveData()
    val schoolName1: MutableLiveData<String> = MutableLiveData()
    val city: MutableLiveData<String> = MutableLiveData()
    val state: MutableLiveData<String> = MutableLiveData()


    fun insertSchool(
        onInsertSchool: (School) -> Unit
    ){
        val newSchool = School(
            _id.value?: return,
            schoolName1.value?: return,
            city.value?: return,
            state.value?: return,
        )
        onInsertSchool(newSchool)
        var idTemporario: Int = _id.value ?: return
        idTemporario++
        _id.value = idTemporario
        schoolName1.value = ""
        city.value = ""
        state.value = ""
    }

    fun updateSchool(
        id: Int,
        onUpdateSchool: (School) -> Unit
    ){
        val school = School(
            id,
            schoolName1.value?: return,
            city.value?: return,
            state.value?:return,

        )
        onUpdateSchool(school)
    }

    fun setIdSchool(index: Int){
        _id.value = index
    }
}

