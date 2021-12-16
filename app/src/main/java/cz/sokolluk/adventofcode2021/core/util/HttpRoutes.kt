package cz.sokolluk.adventofcode2021.core.util

object HttpRoutes {

    private const val BASE = "https://adventofcode.com"

    fun getTaskInputRoute(taskNumber: Int) = "$BASE/2021/day/$taskNumber/input"
}