package cz.sokolluk.adventofcode2021.feature_tasks.domain.use_case

import cz.sokolluk.adventofcode2021.core.util.Resource
import cz.sokolluk.adventofcode2021.feature_tasks.data.service.Task
import cz.sokolluk.adventofcode2021.feature_tasks.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTaskResult @Inject constructor(
    private val repository: TaskRepository
) {
    operator fun invoke(taskNumber: Int): Flow<Resource<Task.Companion.TaskResult>> {
        return repository.getTaskInput(taskNumber)
    }
}