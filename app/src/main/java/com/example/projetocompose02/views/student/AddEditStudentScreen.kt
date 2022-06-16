package com.example.projetocompose02.views.student

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.projetocompose02.data.models.Student
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.filled.Delete


@Composable
fun AddEditStudenScreen(
    navController: NavController,
    student: Student,
    studentViewModel: StudentViewModel,
    addEditStudentViewModel: AddEditStudentViewModel
) {
    Scaffold(
        floatingActionButton = {

            FloatingActionButton(onClick = {

                if (student.id == -1)
                    addEditStudentViewModel.insertStudent(studentViewModel::insert)
                 else
                    addEditStudentViewModel.updateStudent(
                        student.id,
                        studentViewModel::update
                    )

               /* navController.navigate("student"){
                    popUpTo("student"){
                        inclusive = true
                    }*/

                navController.popBackStack()
            }) {
                Icon(imageVector = Icons.Default.Check, contentDescription = "Cadastrar")
            }
        }
    ) {

        addEditStudentViewModel.name.value = student.name
        addEditStudentViewModel.semester.value = student.semester
        addEditStudentViewModel.age.value = student.age
        addEditStudentViewModel.schoolName.value = student.schoolName

        AddEditStudentForm(

            addEditStudentViewModel,
            studentViewModel,
            student,
        ){

            /* navController.navigate("student"){
               popUpTo("student"){
                   inclusive = true
               }*/

            navController.navigate("student")
        }
    }
}

@Composable
fun AddEditStudentForm(

    addEditStudentViewModel: AddEditStudentViewModel,
    studentViewModel: StudentViewModel,
    student: Student,
    navigateBack: () -> Unit,

    ){
    val name = addEditStudentViewModel.name.observeAsState()
    val semester = addEditStudentViewModel.semester.observeAsState()
    val age = addEditStudentViewModel.age.observeAsState()
    val schoolName = addEditStudentViewModel.schoolName.observeAsState()

    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp),

        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                label = {
                    Text(text = "Nome do Aluno: ")
                },
                value = "${name.value}",
                onValueChange = {
                    addEditStudentViewModel.name.value = it
                }
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                label = {
                    Text(text = "Semestre: ")
                },
                value = "${semester.value}",
                onValueChange = {
                    addEditStudentViewModel.semester.value = it
                }
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                label = {
                    Text(text = "Idade: ")
                },
                value = "${age.value}",
                onValueChange = {
                    addEditStudentViewModel.age.value = it
                }
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                label = {
                    Text(text = "Nome da escola: ")
                },
                value = "${schoolName.value}",
                onValueChange = {
                    addEditStudentViewModel.schoolName.value = it
                }
            )
        }
        if(student.id != -1){
            FloatingActionButton(
                modifier = Modifier.padding(16.dp),
                onClick = {
                    studentViewModel.delete(student)
                    navigateBack()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Delete, contentDescription = "Remover"
                )
            }
        }
    }
}
