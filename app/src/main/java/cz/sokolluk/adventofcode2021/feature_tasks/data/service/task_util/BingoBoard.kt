package cz.sokolluk.adventofcode2021.feature_tasks.data.service.task_util

class BingoBoard(val rows: Int, val columns: Int): Matrix(columns = columns, rows = rows) {

    var pickedNumbers = arrayListOf<Int>()

    var hasWon = false

    fun nextPickedNumber(picked: Int): Boolean {

        if (!hasWon) {
            pickedNumbers.add(picked)
            hasWon = isBingo()
        }

        return hasWon
    }

    private fun isBingo(): Boolean {
        var rowWin = false
        var columnWin = false

        (0 until rows).forEach { r ->
            var rowCheck = true
            (0 until columns).forEach { c ->
                if (!pickedNumbers.contains(get(c, r))) rowCheck = false
            }
            if (rowCheck) rowWin = true
        }

        (0 until columns).forEach { c ->
            var columnCheck = true
            (0 until rows).forEach { r ->
                if (!pickedNumbers.contains(get(c, r))) columnCheck = false
            }
            if (columnCheck) columnWin = true
        }

        return rowWin || columnWin
    }

    fun getNotPicked(): List<Int> {
        val ret = ArrayList<Int>()
        (0 until rows).forEach { r ->
            (0 until columns).forEach { c ->
                get(c, r).let {
                    if (!pickedNumbers.contains(it)) ret.add(it)
                }
            }
        }
        return ret
    }


    override fun toString(): String {
        var retStr = ""
        (0 until rows).forEach { r ->
            (0 until columns).forEach { c ->
                retStr += get(c, r).toString() + " "
            }
            retStr += "\n"
        }

        return retStr
    }
}