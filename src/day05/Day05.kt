package day05

import kotlin.io.path.Path
import kotlin.io.path.readLines

object Day05 {

    private const val PATH_TO_INPUT = "src/day05/input.txt"

    private fun isUpdateValid(update: List<Long>, rules: List<Pair<Long, Long>>): Boolean {
        val keys = rules.map { it.first }
        update.forEach { number ->
            if (number in keys) {
                val valuesList = rules.filter { it.first == number }.map { it.second }
                // if value is located before the key, it is invalid
                if (
                    valuesList.any { isValueBeforeKey(key = number, value = it, update = update) }
                ) {
                    return false
                }
            }
        }
        return true
    }

    private fun isValueBeforeKey(key: Long, value: Long, update: List<Long>): Boolean {
        if (update.indexOf(key) == -1 || update.indexOf(value) == -1) {
            return false
        }
        return update.indexOf(key) > update.indexOf(value)
    }

    private fun getMiddleNumberOfList(list: List<Long>): Long {
        return list[list.size / 2]
    }

    fun part01(pathToInput: String = PATH_TO_INPUT): Long {
        // get lines until an empty line is found
        val rules =
            Path(pathToInput)
                .readLines()
                .takeWhile { it.isNotBlank() }
                .map {
                    val (first, second) = it.split("|")
                    first.toLong() to second.toLong()
                }
        // take the remaining lines
        val updates = Path(pathToInput).readLines().dropWhile { it.isNotBlank() }.drop(1)

        var result = 0L
        updates.forEach { updateString ->
            val update = updateString.split(",").map { it.toLong() }
            if (isUpdateValid(update, rules)) {
                result += getMiddleNumberOfList(update)
            }
        }
        return result
    }
}
