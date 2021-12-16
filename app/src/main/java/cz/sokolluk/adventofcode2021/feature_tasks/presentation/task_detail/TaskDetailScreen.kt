package cz.sokolluk.adventofcode2021.feature_tasks.presentation.task_detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import cz.sokolluk.adventofcode2021.ui.theme.GreenLight

@Composable
fun TaskDetailScreen(
    navController: NavController,
    viewModel: TaskDetailViewModel = hiltViewModel()
) {

    val scaffold = rememberScaffoldState()

    Scaffold(scaffoldState = scaffold,
            modifier = Modifier.fillMaxSize()
        ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "<- Back",
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navController.popBackStack() }
                    .padding(16.dp)
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                .fillMaxSize()
            ) {
                Text(
                    text = "Day " + viewModel.state.value.taskNumber,
                    modifier = Modifier.padding(12.dp),
                    color = GreenLight
                )
                Text(
                    text = "Part 1: " + viewModel.state.value.taskResult.firstPart.toString(),
                    modifier = Modifier.padding(12.dp)
                )
                Text(
                    text = "Part 2: " + viewModel.state.value.taskResult.secondPart.toString(),
                    modifier = Modifier.padding(12.dp)
                )
            }
        }
    }

}