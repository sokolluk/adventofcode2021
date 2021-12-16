package cz.sokolluk.adventofcode2021.feature_tasks.presentation.task_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cz.sokolluk.adventofcode2021.feature_tasks.domain.model.Task
import cz.sokolluk.adventofcode2021.ui.theme.GreenLight


@Composable
fun TaskList(
    tasks: List<Task>,
    modifier: Modifier,
    onClick: (Task) -> Unit
) {

    val scroll = rememberScrollState(0)

    Column(
        modifier = modifier
            .padding(16.dp)
            .scrollable(orientation = Orientation.Vertical, state = scroll)
    ) {
        Text(
            text = "Advent of code 2021",
            fontSize = 18.sp,
            color = GreenLight,
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 12.dp)
        )

        tasks.forEach { task ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onClick(task)
                    }
                    .padding(16.dp)
            ) {
                Text(
                    text = task.number.toString() + ":",
                    modifier = Modifier.padding(end = 12.dp)
                )
                Text(
                    text = task.title,
                )
            }
        }
    }
}