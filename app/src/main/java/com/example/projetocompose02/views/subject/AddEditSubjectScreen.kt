package com.example.projetocompose02.views.subject

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.projetocompose02.data.models.Subject


@Composable
fun AddEditSubjectScreen(
    navController: NavController,
    subject: Subject,
    subjectViewModel: SubjectViewModel,
    addEditSubjectViewModel: AddEditSubjectViewModel
) {
    Scaffold(
        floatingActionButton = {

            FloatingActionButton(onClick = {

                if (subject.id == -1)
                    addEditSubjectViewModel.insertSubject(subjectViewModel::insert)
                else
                    addEditSubjectViewModel.updateSubject(
                        subject.id,
                        subjectViewModel::update
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

        addEditSubjectViewModel.name.value = subject.name
        addEditSubjectViewModel.nameCourse.value = subject.nameCourse

        AddEditSubjectForm(
            addEditSubjectViewModel,
            subjectViewModel,
            subject
        ){

            /* navController.navigate("student"){
               popUpTo("student"){
                   inclusive = true
               }*/

            navController.navigate("subject")
        }
    }
}

@Composable
fun AddEditSubjectForm(

    addEditSubjectViewModel: AddEditSubjectViewModel,
    subjectViewModel: SubjectViewModel,
    subject: Subject,
    navigateBack: () -> Unit,

    ) {
    val name = addEditSubjectViewModel.name.observeAsState()
    val nameCourse = addEditSubjectViewModel.nameCourse.observeAsState()

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
                    Text(text = "Nome da Materia: ")
                },
                value = "${name.value}",
                onValueChange = {
                    addEditSubjectViewModel.name.value = it
                }
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                label = {
                    Text(text = "Nome do curso da matéria: ")
                },
                value = "${nameCourse.value}",
                onValueChange = {
                    addEditSubjectViewModel.nameCourse.value = it
                }
            )
        }
        if(subject.id != -1){
            FloatingActionButton(
                modifier = Modifier.padding(16.dp),
                onClick = {
                    subjectViewModel.delete(subject)
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
