package day02

import kotlin.io.path.Path
import kotlin.io.path.readLines

object Day02 {

    private const val PATH_TO_INPUT_01 = "src/day02/input_part01.txt"
    private const val PATH_TO_INPUT_02 = "src/day02/input_part02.txt"

    private fun isLevelSafe(level: List<Long>): Boolean {
        // Trivial case: single or empty list is valid
        if (level.size < 2) return true

        // Check if list is strictly increasing
        val increasing = level.zipWithNext().all { (a, b) -> a < b && (b - a) in 1..3 }

        // Check if list is strictly decreasing
        val decreasing = level.zipWithNext().all { (a, b) -> a > b && (a - b) in 1..3 }

        // If increasing or decreasing, return true
        return increasing || decreasing
    }

    fun part01(pathToInput: String = PATH_TO_INPUT_01): Long {
        val lines = Path(pathToInput).readLines()
        val levels = lines.map { line -> line.split(" ").map { it.toLong() } }
        var result = 0L
        levels.forEach { level -> result += if (isLevelSafe(level)) 1 else 0 }
        return result
    }

    private fun isLevelDampenedSafe(level: List<Long>): Boolean {
        if (isLevelSafe(level)) return true

        for (i in level.indices) {
            val filteredLevel = level.filterIndexed { index, _ -> index != i }
            if (isLevelSafe(filteredLevel)) {
                return true
            }
        }
        return false
    }

    fun part02(pathToInput: String = PATH_TO_INPUT_02): Long {
        val lines = Path(pathToInput).readLines()
        val levels = lines.map { line -> line.split(" ").map { it.toLong() } }
        var result = 0L
        levels.forEach { level ->
            val isDampenedSafe: Boolean = isLevelDampenedSafe(level)
            if (isDampenedSafe) {
                result += 1
            }
            println(isDampenedSafe)
        }
        return result
    }
}
