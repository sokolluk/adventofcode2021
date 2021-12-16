package cz.sokolluk.adventofcode2021.feature_tasks.domain.use_case

import cz.sokolluk.adventofcode2021.core.util.Resource
import cz.sokolluk.adventofcode2021.feature_tasks.domain.model.Task
import cz.sokolluk.adventofcode2021.feature_tasks.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow

class GetTasks(
    private val repository: TaskRepository
) {
    operator fun invoke(): Flow<Resource<List<Task>>> {
        return repository.getTasks()
    }
}