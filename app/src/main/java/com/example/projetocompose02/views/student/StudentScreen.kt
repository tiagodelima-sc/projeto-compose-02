package com.example.projetocompose02.views.student

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.projetocompose02.data.models.Student
import androidx.compose.ui.graphics.Color

@Composable
fun StudentsScreen(
    navController: NavController,
    studentViewModel: StudentViewModel,
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate("student/-1")
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Adicionar Estudante")

            }
        }
    ) {

        val students by studentViewModel.allStudents.observeAsState(listOf())
        //val filter by contactListViewModel.filterBy.observeAsState("")

        Column() {
            StudentList(students, navController)
        }

    }
}

/*@Composable
fun SearchStudent(
    filter: String,
    onFilterChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp),
        label = {
            Row(){
                Icon(imageVector = Icons.Default.Search, contentDescription = "Procurar")
                Text(text = "Procurar")
            }
        },

        value = filter,
        onValueChange = onFilterChange
    )
}
*/

@Composable
fun StudentList(
    students: List<Student>,
    navController: NavController
) {
    LazyColumn() {
        items(students) { student ->
            StudentEntry(student) {
                navController.navigate("student/${student.id}")
            }
        }
    }
}

@Composable
fun StudentEntry(
    student: Student,
    editStudent: () -> Unit
) {

    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .padding(7.dp)
            .clickable {
                expanded = !expanded
            }
            .animateContentSize(
                spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        Column() {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .padding(10.dp)
                        .clip(CircleShape)
                        .size(65.dp)
                        .background(Color.Green),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "${student.id}",
                        style = MaterialTheme.typography.h4
                    )
                }
                Text(
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .weight(1f),
                    text = student.name,
                    style = MaterialTheme.typography.h6
                        .copy(fontWeight = FontWeight.Bold)
                )
                if (expanded) {
                    Icon(
                        modifier = Modifier
                            .padding(5.dp)
                            .size(30.dp)
                            .clickable {
                                editStudent()
                            },
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Editar",
                    )
                }
            }

            if (expanded) {

                student.schoolName?.let {
                    Text(
                        modifier = Modifier.padding(start = 20.dp),
                        text = "Nome da escola: $it",
                        style = MaterialTheme.typography.h6.copy(color = Color.DarkGray)
                    )
                }

                Text(
                    modifier = Modifier.padding(start = 20.dp),
                    text = "Semestre atual: ${student.semester}",
                    style = MaterialTheme.typography.h6.copy(color = Color.DarkGray)
                )

                Text(
                    modifier = Modifier.padding(start = 20.dp),
                    text = "Idade do aluno: ${student.age} anos",
                    style = MaterialTheme.typography.h6.copy(color = Color.DarkGray)
                )
            }
        }
    }
}


