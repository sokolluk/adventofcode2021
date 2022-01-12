package cz.sokolluk.adventofcode2021.feature_tasks.data.service.tasks

import android.util.Log
import cz.sokolluk.adventofcode2021.feature_tasks.data.service.Task
import cz.sokolluk.adventofcode2021.feature_tasks.data.service.task_util.Matrix
import java.lang.Exception
import kotlin.math.abs
import kotlin.math.max

class Task5: Task {
    override suspend fun computeFirstPart(input: String): String? {

        val lines = input.split("\n").map { Line.parseLine(it) }

        val endX = lines.map { max(it.begin.x, it.end.x) }.maxOrNull() ?: 0
        val endY = lines.map { max(it.begin.y, it.end.y) }.maxOrNull() ?: 0

        val map = CloudMap(endX + 1, endY + 1)

        try {
            map.drawClouds(lines)
        } catch (e: Exception) {
            Log.e("ERROR", "ERROR", e)
        }

        return map.countOverlappingClouds().toString()
    }

    override suspend fun computeSecondPart(input: String): String? {
        val lines = input.split("\n").map { Line.parseLine(it) }

        val endX = lines.map { max(it.begin.x, it.end.x) }.maxOrNull() ?: 0
        val endY = lines.map { max(it.begin.y, it.end.y) }.maxOrNull() ?: 0

        val map = CloudMap(endX + 1, endY + 1)

        try {
            map.drawClouds(lines, true)
        } catch (e: Exception) {
            Log.e("ERROR", "ERROR", e)
        }

        return map.countOverlappingClouds().toString()
    }



    data class Point(
        val x: Int,
        val y: Int
    ) {
        companion object {
            fun parsePoint(input: String): Point {
                return input.split(",").let { s ->
                    Point(x = s[0].toInt(), y = s[1].toInt())
                }
            }
        }
    }

    class Line(
        val begin: Point,
        val end: Point
    ) {
        fun isVertical(): Boolean = begin.y == end.y
        fun isHorizontal(): Boolean = begin.x == end.x
        fun isDiagonal(): Boolean = abs(begin.x - end.x) == abs(begin.y - end.y)

        companion object {
            fun parseLine(input: String): Line {
                return input.replace(" ", "").split("->").let { s ->
                    Line(
                        begin = Point.parsePoint(s[0]),
                        end = Point.parsePoint(s[1])
                    )
                }
            }
        }
    }

    class CloudMap(val rows: Int, val columns: Int): Matrix(columns = columns, rows = rows) {

        fun drawClouds(lines: List<Line>, drawDiagonal: Boolean = false) {

            lines.forEach { line ->
                // draw horizontal line
                if (line.isHorizontal()) {
                    drawHorizontal(line)
                }
                // draw vertical line
                if (line.isVertical()) {
                    drawVertical(line)
                }
                // draw diagonal line
                if (drawDiagonal && line.isDiagonal()) {
                    drawDiagonal(line)
                }
            }
        }

        private fun drawHorizontal(line: Line) {
            val start = if (line.begin.y > line.end.y) line.end.y else line.begin.y
            val end = if (line.begin.y > line.end.y) line.begin.y else line.end.y
            (start .. end).forEach { y ->
                set(y, line.begin.x, get(y, line.begin.x) + 1)
            }
        }

        private fun drawVertical(line: Line) {
            val start = if (line.begin.x > line.end.x) line.end.x else line.begin.x
            val end = if (line.begin.x > line.end.x) line.begin.x else line.end.x
            (start .. end).forEach { x ->
                set(line.begin.y, x, get(line.begin.y, x) + 1)
            }
        }

        private fun drawDiagonal(line: Line) {
            val steps = abs(line.begin.x - line.end.x)
            val increasingX = line.begin.x < line.end.x
            val increasingY = line.begin.y < line.end.y
            (0 .. steps).forEach { step ->

                val changeX = if (increasingX) step else -step
                val changeY = if (increasingY) step else -step

                val y = line.begin.y + changeY
                val x = line.begin.x + changeX

                set(y, x, get(y, x) + 1)
            }
        }

        fun countOverlappingClouds(): Int {
            var count = 0
            (0 until rows).forEach { r ->
                (0 until columns).forEach { c ->
                    if (get(c, r) > 1) {
                        count += 1
                    }
                }
            }
            return count
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
}