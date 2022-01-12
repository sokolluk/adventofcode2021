package cz.sokolluk.adventofcode2021.feature_tasks.data.repository

import cz.sokolluk.adventofcode2021.core.util.Resource
import cz.sokolluk.adventofcode2021.feature_tasks.data.service.TaskService
import cz.sokolluk.adventofcode2021.feature_tasks.domain.model.Task
import cz.sokolluk.adventofcode2021.feature_tasks.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import cz.sokolluk.adventofcode2021.feature_tasks.data.service.Task.Companion.TaskResult

class TaskRepositoryImpl(
    private val service: TaskService
): TaskRepository {
    override fun getTasks(): Flow<Resource<List<Task>>> = flow {
        emit(Resource.Success(listOf(
            Task(1,"Sonar Sweep"),
            Task(2,"Dive!"),
            Task(3,"Binary Diagnostic"),
            Task(4,"Giant Squid"),
            Task(5,"Hydrothermal Venture"),
        )))
    }

    override fun getTaskInput(taskNumber: Int): Flow<Resource<TaskResult>> = flow<Resource<TaskResult>> {
        emit(Resource.Loading())
        try {
            val result = service.getTaskResult(taskNumber = taskNumber)
            emit(Resource.Success(result))
        } catch (t: Throwable) {
            emit(Resource.Error(t.localizedMessage ?: "No message"))
        }
    }
}