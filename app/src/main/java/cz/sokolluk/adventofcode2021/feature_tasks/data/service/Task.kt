package cz.sokolluk.adventofcode2021.feature_tasks.data.service

interface Task {
    suspend fun computeFirstPart(input: String): String?
    suspend fun computeSecondPart(input: String): String?

    companion object {
        data class TaskResult(
            val firstPart: String? = null,
            val secondPart: String? = null
        )
    }
}