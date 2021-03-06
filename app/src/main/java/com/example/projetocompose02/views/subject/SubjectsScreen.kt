package com.example.projetocompose02.views.subject

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.projetocompose02.data.models.Student
import com.example.projetocompose02.data.models.Subject
import com.example.projetocompose02.views.student.StudentList

@Composable

fun SubjectsScreen(
    navController: NavController,
    subjectViewModel: SubjectViewModel
){
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate("subject/-1")
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Adicionar Materia")

            }
        }
    ) {

        val subjects by subjectViewModel.allSubjects.observeAsState(listOf())

        Column() {
            SubjectList(subjects, navController)
        }

    }
}

@Composable
fun SubjectList(
    subjects: List<Subject>,
    navController: NavController
) {
    LazyColumn(){
        items(subjects){
            SubjectEntry(it){
                navController.navigate("subject/${it.id}")
            }
        }
    }
}

@Composable
fun SubjectEntry(
    subject: Subject,
    editSubject: () -> Unit
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
                        text = "${subject.id}",
                        style = MaterialTheme.typography.h4
                    )
                }
                Text(
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .weight(1f),
                    text = subject.name,
                    style = MaterialTheme.typography.h6
                        .copy(fontWeight = FontWeight.Bold)
                )
                if (expanded) {
                    Icon(
                        modifier = Modifier
                            .padding(5.dp)
                            .size(30.dp)
                            .clickable {
                                editSubject()
                            },
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Editar",
                    )
                }
            }

            if (expanded) {

                subject.name?.let {
                    Text(
                        modifier = Modifier.padding(start = 20.dp),
                        text = "Nome da Materia: $it",
                        style = MaterialTheme.typography.h6.copy(color = Color.DarkGray)
                    )
                }

                Text(
                    modifier = Modifier.padding(start = 20.dp),
                    text = "Nome do Curso: ${subject.nameCourse}",
                    style = MaterialTheme.typography.h6.copy(color = Color.DarkGray)
                )

            }
        }
    }
}
