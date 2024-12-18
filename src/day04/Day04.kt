package day04

import kotlin.io.path.Path
import kotlin.io.path.readLines

object Day04 {

    private const val PATH_TO_INPUT = "src/day04/input.txt"

    private fun verticalOrReverseCount(input: List<List<Char>>): Int {
        var count = 0
        for (lineIndex in input.indices) {
            for (charIndex in input[lineIndex].indices) {
                if (lineIndex + 3 < input.size) {
                    if (
                        input[lineIndex][charIndex] == 'X' &&
                            input[lineIndex + 1][charIndex] == 'M' &&
                            input[lineIndex + 2][charIndex] == 'A' &&
                            input[lineIndex + 3][charIndex] == 'S'
                    ) {
                        count++
                    }
                }
                // reverse XMAS
                if (lineIndex - 3 >= 0) {
                    if (
                        input[lineIndex][charIndex] == 'X' &&
                            input[lineIndex - 1][charIndex] == 'M' &&
                            input[lineIndex - 2][charIndex] == 'A' &&
                            input[lineIndex - 3][charIndex] == 'S'
                    ) {
                        count++
                    }
                }
            }
        }
        return count
    }

    private fun horizontalOrReverseCount(input: List<List<Char>>): Int {
        var count = 0
        for (lineIndex in input.indices) {
            for (charIndex in input[lineIndex].indices) {
                if (charIndex + 3 < input[lineIndex].size) {
                    if (
                        input[lineIndex][charIndex] == 'X' &&
                            input[lineIndex][charIndex + 1] == 'M' &&
                            input[lineIndex][charIndex + 2] == 'A' &&
                            input[lineIndex][charIndex + 3] == 'S'
                    ) {
                        count++
                    }
                }
                // reverse XMAS
                if (charIndex - 3 >= 0) {
                    if (
                        input[lineIndex][charIndex] == 'X' &&
                            input[lineIndex][charIndex - 1] == 'M' &&
                            input[lineIndex][charIndex - 2] == 'A' &&
                            input[lineIndex][charIndex - 3] == 'S'
                    ) {
                        count++
                    }
                }
            }
        }
        return count
    }

    private fun diagonalOrReverseCount(input: List<List<Char>>): Int {
        var count = 0
        for (lineIndex in input.indices) {
            for (charIndex in input[lineIndex].indices) {
                // top left to bottom right diagonal
                if (lineIndex + 3 < input.size && charIndex + 3 < input[lineIndex].size) {
                    if (
                        input[lineIndex][charIndex] == 'X' &&
                            input[lineIndex + 1][charIndex + 1] == 'M' &&
                            input[lineIndex + 2][charIndex + 2] == 'A' &&
                            input[lineIndex + 3][charIndex + 3] == 'S'
                    ) {
                        count++
                    }
                }
                // bottom right to top left diagonal
                if (lineIndex - 3 >= 0 && charIndex - 3 >= 0) {
                    if (
                        input[lineIndex][charIndex] == 'X' &&
                            input[lineIndex - 1][charIndex - 1] == 'M' &&
                            input[lineIndex - 2][charIndex - 2] == 'A' &&
                            input[lineIndex - 3][charIndex - 3] == 'S'
                    ) {
                        count++
                    }
                }

                // top right to bottom left diagonal
                if (lineIndex + 3 < input.size && charIndex - 3 >= 0) {
                    if (
                        input[lineIndex][charIndex] == 'X' &&
                            input[lineIndex + 1][charIndex - 1] == 'M' &&
                            input[lineIndex + 2][charIndex - 2] == 'A' &&
                            input[lineIndex + 3][charIndex - 3] == 'S'
                    ) {
                        count++
                    }
                }

                // bottom left to top right diagonal
                if (lineIndex - 3 >= 0 && charIndex + 3 < input[lineIndex].size) {
                    if (
                        input[lineIndex][charIndex] == 'X' &&
                            input[lineIndex - 1][charIndex + 1] == 'M' &&
                            input[lineIndex - 2][charIndex + 2] == 'A' &&
                            input[lineIndex - 3][charIndex + 3] == 'S'
                    ) {
                        count++
                    }
                }
            }
        }

        return count
    }

    fun part01(pathToInput: String = PATH_TO_INPUT): Int {
        val input = Path(pathToInput).readLines().map { it.toList() }
        return (verticalOrReverseCount(input) +
            horizontalOrReverseCount(input) +
            diagonalOrReverseCount(input))
    }

    private fun xShapedMASCount(input: List<List<Char>>): Int {
        var count = 0
        // find the center of the X-MAS which is the letter 'A'
        for (lineIndex in input.indices) {
            for (charIndex in input[lineIndex].indices) {
                // check if there's room for the X-MAS to be in the shape of an X
                if (
                    input[lineIndex][charIndex] == 'A' &&
                        lineIndex - 1 >= 0 &&
                        lineIndex + 1 < input.size &&
                        charIndex - 1 >= 0 &&
                        charIndex + 1 < input[lineIndex].size
                ) {
                    // check if there's a MAS or SAM on the diagonals forwards and backwards
                    if (
                        ((input[lineIndex - 1][charIndex - 1] == 'M' &&
                            input[lineIndex + 1][charIndex + 1] == 'S') ||
                            (input[lineIndex - 1][charIndex - 1] == 'S' &&
                                input[lineIndex + 1][charIndex + 1] == 'M')) &&
                            ((input[lineIndex - 1][charIndex + 1] == 'M' &&
                                input[lineIndex + 1][charIndex - 1] == 'S') ||
                                (input[lineIndex - 1][charIndex + 1] == 'S' &&
                                    input[lineIndex + 1][charIndex - 1] == 'M'))
                    ) {
                        count++
                    }
                }
            }
        }
        return count
    }

    fun part02(pathToInput: String = PATH_TO_INPUT): Int {
        val input = Path(pathToInput).readLines().map { it.toList() }
        return xShapedMASCount(input)
    }
}
