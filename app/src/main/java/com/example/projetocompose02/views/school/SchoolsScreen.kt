package com.example.projetocompose02.views.school

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
import com.example.projetocompose02.data.models.School


@Composable
fun SchoolsScreen(
    navController: NavController,
    schoolViewModel: SchoolViewModel,
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate("school/-1")
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Adicionar Escola")

            }
        }
    ) {

        val school by schoolViewModel.allSchool.observeAsState(listOf())

        Column() {
            SchoolList(school, navController)
        }

    }
}

@Composable
fun SchoolList(
    schools: List<School>,
    navController: NavController
) {
    LazyColumn() {
        items(schools) { school ->
            SchoolEntry(school) {
                navController.navigate("school/${school.id}")
            }
        }
    }
}

@Composable
fun SchoolEntry(
    school: School,
    editSchool: () -> Unit
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
                        text = "${school.id}",
                        style = MaterialTheme.typography.h4
                    )
                }
                Text(
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .weight(1f),
                    text = school.schoolName1,
                    style = MaterialTheme.typography.h6
                        .copy(fontWeight = FontWeight.Bold)
                )
                if (expanded) {
                    Icon(
                        modifier = Modifier
                            .padding(5.dp)
                            .size(30.dp)
                            .clickable {
                                editSchool()
                            },
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Editar",
                    )
                }
            }

            if (expanded) {

                school.schoolName1?.let {
                    Text(
                        modifier = Modifier.padding(start = 20.dp),
                        text = "Nome da escola: $it",
                        style = MaterialTheme.typography.h6.copy(color = Color.DarkGray)
                    )
                }

                Text(
                    modifier = Modifier.padding(start = 20.dp),
                    text = "Cidade: ${school.city}",
                    style = MaterialTheme.typography.h6.copy(color = Color.DarkGray)
                )

                Text(
                    modifier = Modifier.padding(start = 20.dp),
                    text = "Estado: ${school.state}",
                    style = MaterialTheme.typography.h6.copy(color = Color.DarkGray)
                )
            }
        }
    }
}



