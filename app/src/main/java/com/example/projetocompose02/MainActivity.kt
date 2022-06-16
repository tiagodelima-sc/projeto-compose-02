package com.example.projetocompose02

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.projetocompose02.ui.theme.ProjetoCompose02Theme
import com.example.projetocompose02.views.school.AddEditSchoolScreen
import com.example.projetocompose02.views.school.AddEditSchoolViewModel
import com.example.projetocompose02.views.school.SchoolViewModel
import com.example.projetocompose02.views.student.StudentViewModel
import com.example.projetocompose02.views.subject.SubjectViewModel
import com.example.projetocompose02.views.school.SchoolsScreen
import com.example.projetocompose02.views.student.AddEditStudenScreen
import com.example.projetocompose02.views.student.AddEditStudentViewModel
import com.example.projetocompose02.views.student.StudentsScreen
import com.example.projetocompose02.views.subject.AddEditSubjectScreen
import com.example.projetocompose02.views.subject.AddEditSubjectViewModel
import com.example.projetocompose02.views.subject.SubjectsScreen



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (this.applicationContext as SchoolApplication).schooldatabase.studentDao()

        val subjectViewModel: SubjectViewModel by viewModels<SubjectViewModel> {
            SubjectViewModel.SubjectViewModelFactory(
                (this.applicationContext as SchoolApplication).schooldatabase.subjectDao()
            )
        }

        val studentViewModel: StudentViewModel by viewModels<StudentViewModel> {
            StudentViewModel.StudentViewModelFactory(
                (this.applicationContext as SchoolApplication).schooldatabase.studentDao()
            )
        }

        val schoolViewModel: SchoolViewModel by viewModels<SchoolViewModel> {
            SchoolViewModel.SchoolViewModelFactory(
                (this.applicationContext as SchoolApplication).schooldatabase.schoolDao()
            )
        }

        val addEditSchoolViewModel: AddEditSchoolViewModel by viewModels()
        addEditSchoolViewModel.setIdSchool(schoolViewModel.getLastIdSchool())

        val addEditSubjectViewModel: AddEditSubjectViewModel by viewModels()
        addEditSubjectViewModel.setIdSubject(subjectViewModel.getLastIdSubject())


        val addEditStudentViewModel: AddEditStudentViewModel by viewModels()
        addEditStudentViewModel.setIdStudent(studentViewModel.getLastIdStudent())

        setContent {
            ProjetoCompose02Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    SchoolApp(
                        subjectViewModel,
                        studentViewModel,
                        schoolViewModel,
                        addEditSubjectViewModel,
                        addEditStudentViewModel,
                        addEditSchoolViewModel
                    )
                }
            }
        }
    }
}


@Composable
fun SchoolApp(
    subjectViewModel: SubjectViewModel,
    studentViewModel: StudentViewModel,
    schoolViewModel: SchoolViewModel,
    addEditSubjectViewModel: AddEditSubjectViewModel,
    addEditStudentViewModel: AddEditStudentViewModel,
    addEditSchoolViewModel: AddEditSchoolViewModel
) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomAppBar(
                modifier = Modifier.height(80.dp)
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                bottomNavScreens.forEach { botNavScreen ->
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                modifier = Modifier.size(45.dp),
                                painter = painterResource(id = botNavScreen.icon),
                                contentDescription = stringResource(id = botNavScreen.name)
                            )
                        },
                        label = { Text(text = stringResource(id = botNavScreen.name)) },
                        selected = currentDestination?.hierarchy?.any {
                            it.route == botNavScreen.route
                        } == true,
                        onClick = {
                            navController.navigate(botNavScreen.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) {
        NavHost(
            modifier = Modifier.padding(it),
            navController = navController,
            startDestination = ScreenApp.SchoolsScreen.route
        ) {
            composable(ScreenApp.SchoolsScreen.route) {
                SchoolsScreen(navController, schoolViewModel)
            }
            composable(ScreenApp.StudentsScreen.route) {
                StudentsScreen(navController, studentViewModel)
            }
            composable(ScreenApp.SubjectsScreen.route) {
                SubjectsScreen(navController, subjectViewModel)
            }

            composable(
                route = "student/{id}",
                arguments = listOf(navArgument("id"){
                    defaultValue = -1
                    type = NavType.IntType
                })
            ){
                val id = it.arguments?.getInt("id") ?: -1
                val student = studentViewModel.getStudent(id)
                AddEditStudenScreen(
                    navController,
                    student,
                    studentViewModel,
                    addEditStudentViewModel
                )
            }

            composable(
                route = "subject/{id}",
                arguments = listOf(navArgument("id"){
                    defaultValue = -1
                    type = NavType.IntType
                })
            ){
                val subject = subjectViewModel.getSubject(
                    it.arguments?.getInt("id") ?: -1
                )
                AddEditSubjectScreen(
                    navController,
                    subject,
                    subjectViewModel,
                    addEditSubjectViewModel,
                )
            }

            composable(
                route = "school/{id}",
                arguments = listOf(navArgument("id"){
                    defaultValue = -1
                    type = NavType.IntType
                })
            ){
                val school = schoolViewModel.getSchool(
                    it.arguments?.getInt("id") ?: -1
                )
                AddEditSchoolScreen(
                    navController,
                    school,
                    schoolViewModel,
                    addEditSchoolViewModel
                )
            }

            composable(ScreenApp.SchoolDetails.route) {

            }
            composable(ScreenApp.StudentDetails.route) {

            }
            composable(ScreenApp.SubjectDetails.route) {

            }
        }
    }
}

private val bottomNavScreens = listOf(
    ScreenApp.SchoolsScreen,
    ScreenApp.SubjectsScreen,
    ScreenApp.StudentsScreen
)

sealed class ScreenApp(
    val route: String,
    @DrawableRes val icon: Int,
    @StringRes val name: Int,
) {
    object SubjectsScreen : ScreenApp("subject", R.drawable.subject, R.string.subject)
    object StudentsScreen : ScreenApp("student", R.drawable.student, R.string.student)
    object SchoolsScreen : ScreenApp("school", R.drawable.school, R.string.school)
    object SubjectDetails : ScreenApp("subject_details", R.drawable.subject, R.string.subject)
    object StudentDetails : ScreenApp("student_details", R.drawable.student, R.string.student)
    object SchoolDetails : ScreenApp("school_details", R.drawable.school, R.string.school)
}