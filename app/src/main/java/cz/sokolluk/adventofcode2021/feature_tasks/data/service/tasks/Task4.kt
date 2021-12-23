package cz.sokolluk.adventofcode2021.feature_tasks.data.service.tasks

import android.util.Log
import cz.sokolluk.adventofcode2021.feature_tasks.data.service.Task
import cz.sokolluk.adventofcode2021.feature_tasks.data.service.task_util.BingoBoard

class Task4: Task {
    override suspend fun computeFirstPart(input: String): String? {

        val data = input.split("\n")

        val inputs = data[0]

        val bingoBoards = parseBingoBoards(data)

        var result: Int? = null

        run ope@ {
            inputs.split(",").map { it.toInt() }.forEach { picked ->
                bingoBoards.forEachIndexed { i, bingo ->
                    if (bingo.nextPickedNumber(picked)) {
                        Log.e("TASK4", "winner is $i th bingo")
                        Log.e("TASK4", bingo.pickedNumbers.toString())
                        Log.e("TASK4", bingo.toString())
                        Log.e("TASK4", bingo.getNotPicked().toString())
                        result = bingo.getNotPicked().sum() * picked
                        return@ope
                    }
                }
            }
        }

        return result?.toString()
    }

    override suspend fun computeSecondPart(input: String): String? {
        val data = input.split("\n")

        val inputs = data[0]

        val bingoBoards = parseBingoBoards(data)

        var lastResult: Int? = null

        inputs.split(",").map { it.toInt() }.forEach { picked ->
            bingoBoards.forEachIndexed { i, bingo ->
                if (!bingo.hasWon && bingo.nextPickedNumber(picked)) {
                    Log.e("TASK4", "winner is $i th bingo")
                    Log.e("TASK4", bingo.pickedNumbers.toString())
                    Log.e("TASK4", bingo.toString())
                    Log.e("TASK4", bingo.getNotPicked().toString())
                    lastResult = bingo.getNotPicked().sum() * picked
                }
            }
        }

        return lastResult?.toString()
    }

    private fun parseBingoBoards(data: List<String>): ArrayList<BingoBoard> {

        val bingoInput = arrayListOf<String>()

        val bingoBoards = arrayListOf<BingoBoard>()

        data.forEachIndexed { i, inputRow ->
            if (i > 0 && inputRow.isNotEmpty()) {
                bingoInput.add(inputRow.replace("  ", " "))
                if (bingoInput.size == 5) {
                    val newBingoBoard = BingoBoard(5, 5)
                    newBingoBoard.initFromStringRows(bingoInput, " ")
                    bingoBoards.add(newBingoBoard)
                    bingoInput.clear()
                }
            }
        }

        return bingoBoards
    }

}