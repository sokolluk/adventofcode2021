package cz.sokolluk.adventofcode2021.feature_tasks.domain.repository

import cz.sokolluk.adventofcode2021.core.util.Resource
import cz.sokolluk.adventofcode2021.feature_tasks.domain.model.Task
import cz.sokolluk.adventofcode2021.feature_tasks.data.service.Task.Companion.TaskResult
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    fun getTasks(): Flow<Resource<List<Task>>>
    fun getTaskInput(taskNumber: Int): Flow<Resource<TaskResult>>
}