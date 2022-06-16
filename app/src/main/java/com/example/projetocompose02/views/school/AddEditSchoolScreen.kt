package com.example.projetocompose02.views.school

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
import com.example.projetocompose02.data.models.School


@Composable
fun AddEditSchoolScreen(
    navController: NavController,
    school: School,
    schoolViewModel: SchoolViewModel,
    addEditSchoolViewModel: AddEditSchoolViewModel
) {
    Scaffold(
        floatingActionButton = {

            FloatingActionButton(onClick = {

                if (school.id == -1)
                    addEditSchoolViewModel.insertSchool(schoolViewModel::insert)
                else
                    addEditSchoolViewModel.updateSchool(
                        school.id,
                        schoolViewModel::update
                    )

                /* navController.navigate("school"){
                     popUpTo("student"){
                         inclusive = true
                     }*/

                navController.popBackStack()
            }) {
                Icon(imageVector = Icons.Default.Check, contentDescription = "Cadastrar")
            }
        }
    ) {

        addEditSchoolViewModel.schoolName1.value = school.schoolName1
        addEditSchoolViewModel.city.value = school.city
        addEditSchoolViewModel.state.value = school.state

        AddEditSchoolForm(

            addEditSchoolViewModel,
            schoolViewModel,
            school,
        ){

            navController.navigate("school")
        }
    }
}

@Composable
fun AddEditSchoolForm(

    addEditSchoolViewModel: AddEditSchoolViewModel,
    schoolViewModel: SchoolViewModel,
    school: School,
    navigateBack: () -> Unit,

    ){
    val schoolName1 = addEditSchoolViewModel.schoolName1.observeAsState()
    val city = addEditSchoolViewModel.city.observeAsState()
    val state = addEditSchoolViewModel.state.observeAsState()

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
                    Text(text = "Nome da Escola: ")
                },
                value = "${schoolName1.value}",
                onValueChange = {
                    addEditSchoolViewModel.schoolName1.value = it
                }
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                label = {
                    Text(text = "Cidade: ")
                },
                value = "${city.value}",
                onValueChange = {
                    addEditSchoolViewModel.city.value = it
                }
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                label = {
                    Text(text = "Estado: ")
                },
                value = "${state.value}",
                onValueChange = {
                    addEditSchoolViewModel.state.value = it
                }
            )
        }
        if(school.id != -1){
            FloatingActionButton(
                modifier = Modifier.padding(16.dp),
                onClick = {
                    schoolViewModel.delete(school)
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
