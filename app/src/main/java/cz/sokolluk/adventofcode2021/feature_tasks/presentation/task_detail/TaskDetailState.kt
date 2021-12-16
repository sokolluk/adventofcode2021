package cz.sokolluk.adventofcode2021.feature_tasks.presentation.task_detail

import cz.sokolluk.adventofcode2021.feature_tasks.data.service.Task

data class TaskDetailState(
    val taskNumber: Int = -1,
    val taskResult: Task.Companion.TaskResult = Task.Companion.TaskResult(),
    val isLoading: Boolean = false
)