package day01

import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.math.abs

object Day01 {
    private const val PATH_TO_INPUT = "src/day01/input.txt"

    fun result(pathToInput: String = PATH_TO_INPUT): Long {
        val lines = Path(pathToInput).readLines()
        val (leftList, rightList) =
            lines
                .map { line ->
                    val left = line.substringBefore(" ").toLong()
                    val right = line.substringAfterLast(" ").toLong()
                    left to right
                }
                .unzip()
        val distanceBetweenLists =
            leftList.sorted().zip(rightList.sorted()).sumOf { (first, second) ->
                abs(first - second)
            }
        return distanceBetweenLists
    }
}