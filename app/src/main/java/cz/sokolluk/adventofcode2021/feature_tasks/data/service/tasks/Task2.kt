package cz.sokolluk.adventofcode2021.feature_tasks.data.service.tasks

import cz.sokolluk.adventofcode2021.feature_tasks.data.service.Task

class Task2: Task {
    override suspend fun computeFirstPart(input: String): String? {
        var depth = 0
        var forward = 0
        input.split("\n").map { parseInstruction(it) }.forEach { instruction ->
            when(instruction?.command) {
                Command.Up -> depth -= instruction.value
                Command.Down -> depth += instruction.value
                Command.Forward -> forward += instruction.value
                else -> {}
            }
        }
        return (depth * forward).toString()
    }

    override suspend fun computeSecondPart(input: String): String? {
        var depth = 0
        var forward = 0
        var aim = 0
        input.split("\n").map { parseInstruction(it) }.forEach { instruction ->
            when(instruction?.command) {
                Command.Up -> aim -= instruction.value
                Command.Down -> aim += instruction.value
                Command.Forward -> {
                    forward += instruction.value
                    depth += aim * instruction.value
                }
                else -> {}
            }
        }
        return (depth * forward).toString()
    }

    private fun parseInstruction(instruction: String): Instruction? {
        val split = instruction.split(" ")
        if (split.size != 2) return null

        return Instruction(
            command = Command.parse(split[0]),
            value = split[1].toInt()
        )
    }

    data class Instruction(
        val command: Command,
        val value: Int
    )

    enum class Command() {
        Up,
        Down,
        Forward;
        companion object {
            fun parse(commandStr: String): Command {
                return values().firstOrNull{ it.name.equals(commandStr, true)} ?: Up
            }
        }
    }
}