package cz.sokolluk.adventofcode2021.feature_tasks.data.service.task_util

open class Matrix(columns: Int, rows: Int) {
    protected val matrix = Array(rows) { IntArray(columns) { 0 } }

    fun set(c: Int, r: Int, value: Int) {
        matrix[r][c] = value
    }

    fun get(c: Int, r: Int): Int {
        return matrix[r][c]
    }

    fun initFromStringRows(rows: List<String>, separator: String) {
        rows.forEachIndexed { r, row ->
            row.split(separator).filter { it.isNotEmpty() }.map { it.toInt() }.forEachIndexed { c, value ->
                set(c, r, value)
            }
        }
    }
}