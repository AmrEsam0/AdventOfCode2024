package day01

import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.math.abs

object Day01 {
    private const val PATH_TO_INPUT_01 = "src/day01/input_part01.txt"
    private const val PATH_TO_INPUT_02 = "src/day01/input_part02.txt"

    fun part01(pathToInput: String = PATH_TO_INPUT_01): Long {
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

    fun part02(pathToInput: String = PATH_TO_INPUT_02): Long {
        val lines = Path(pathToInput).readLines()
        val (leftList, rightList) =
            lines
                .map { line ->
                    val left = line.substringBefore(" ").toLong()
                    val right = line.substringAfterLast(" ").toLong()
                    left to right
                }
                .unzip()

        var similarityScore = 0L
       leftList.forEach { left ->
           similarityScore += (rightList.count { it == left }) * left
       }
        return similarityScore
    }
}
