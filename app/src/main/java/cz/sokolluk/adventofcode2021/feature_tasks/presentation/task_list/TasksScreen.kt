package cz.sokolluk.adventofcode2021.feature_tasks.presentation.task_list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import cz.sokolluk.adventofcode2021.feature_tasks.presentation.util.Screen

@Composable
fun TasksScreen(
    navController: NavController,
    viewModel: TaskLIstViewModel = hiltViewModel()
)  {
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize()
    ) {
        TaskList(
            tasks = state.tasks,
            modifier = Modifier.fillMaxSize()
        ) { task ->
            navController.navigate(
                Screen.TaskDetailScreen.createRoute(task.number)
            )
        }
    }
}