package cz.sokolluk.adventofcode2021.feature_tasks.data.local

import android.content.Context
import cz.sokolluk.adventofcode2021.feature_tasks.data.service.Task
import cz.sokolluk.adventofcode2021.feature_tasks.data.service.TaskResolver
import cz.sokolluk.adventofcode2021.feature_tasks.data.service.TaskService
import java.io.InputStream


class TaskServiceImpl(
    private val context: Context
): TaskService {
    override suspend fun getTaskResult(taskNumber: Int): Task.Companion.TaskResult {
        return try {
            val input = readFile(context, "task$taskNumber.txt")
            TaskResolver.resolve(taskNumber, input)
        } catch (t: Throwable) {
            println(t.localizedMessage ?: "no error")
            Task.Companion.TaskResult()
        }
    }

    private fun readFile(context: Context, fileName: String): String {
        val inputStream: InputStream = context.assets.open(fileName)
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        return String(buffer)
    }
}