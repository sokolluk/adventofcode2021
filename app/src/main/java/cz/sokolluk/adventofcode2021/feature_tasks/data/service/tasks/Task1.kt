package cz.sokolluk.adventofcode2021.feature_tasks.data.service.tasks

import cz.sokolluk.adventofcode2021.feature_tasks.data.service.Task

class Task1: Task {
    override suspend fun computeFirstPart(input: String): String? {
        var result: Int = 0
        var prev: Int = -1
        input.split("\n").forEach { number ->
            val current = number.toInt()
            if (prev > -1 && current > prev) result++
            prev = current
        }
        return result.toString()
    }

    override suspend fun computeSecondPart(input: String): String? {

        val splitted = input.split("\n").map { it.toInt() }
        var prev = -1
        var result = 0

        splitted.forEachIndexed { i, number ->
            if (i < splitted.size - 2) {
                val next = splitted[i+1]
                val next2 = splitted[i+2]
                val current = number + next + next2
                if (prev > -1 && current > prev) result++
                prev = current
            }
        }
        return result.toString()
    }
}