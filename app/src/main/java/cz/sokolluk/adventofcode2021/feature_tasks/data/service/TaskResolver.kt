package cz.sokolluk.adventofcode2021.feature_tasks.data.service

import cz.sokolluk.adventofcode2021.feature_tasks.data.service.tasks.Task1
import cz.sokolluk.adventofcode2021.feature_tasks.data.service.tasks.Task2
import cz.sokolluk.adventofcode2021.feature_tasks.data.service.tasks.Task3
import cz.sokolluk.adventofcode2021.feature_tasks.data.service.tasks.Task4

object TaskResolver {

    suspend fun resolve(taskNumber: Int, input: String): Task.Companion.TaskResult {
        return when(taskNumber) {
            1 -> Task.Companion.TaskResult(
                Task1().computeFirstPart(input),
                Task1().computeSecondPart(input)
            )
            2 -> Task.Companion.TaskResult(
                Task2().computeFirstPart(input),
                Task2().computeSecondPart(input)
            )
            3 -> Task.Companion.TaskResult(
                Task3().computeFirstPart(input),
                Task3().computeSecondPart(input)
            )
            4 -> Task.Companion.TaskResult(
                Task4().computeFirstPart(input),
                Task4().computeSecondPart(input)
            )
            else -> Task.Companion.TaskResult()
        }
    }
}