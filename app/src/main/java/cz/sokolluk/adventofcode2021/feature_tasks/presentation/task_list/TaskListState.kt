package cz.sokolluk.adventofcode2021.feature_tasks.presentation.task_list

import cz.sokolluk.adventofcode2021.feature_tasks.domain.model.Task

data class TaskListState(
    val tasks: List<Task> = emptyList(),
    val isLoading: Boolean = false
)