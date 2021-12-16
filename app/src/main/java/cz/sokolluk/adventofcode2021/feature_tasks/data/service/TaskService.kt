package cz.sokolluk.adventofcode2021.feature_tasks.data.service

import android.content.Context
import cz.sokolluk.adventofcode2021.feature_tasks.data.local.TaskServiceImpl

interface TaskService {
    suspend fun getTaskResult(taskNumber: Int): Task.Companion.TaskResult

    companion object {
        fun create(context: Context): TaskService {
            return TaskServiceImpl(context)
        }
    }
}