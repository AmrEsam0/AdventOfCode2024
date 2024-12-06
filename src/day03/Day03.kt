package day03

import kotlin.io.path.Path
import kotlin.io.path.readText

object Day03 {
    private const val PATH_TO_INPUT = "src/day03/input.txt"

    private val mulRegex = """mul\((\d{1,3}),(\d{1,3})\)""".toRegex()

    private fun String.solveOneMul(): Int {
        val (a, b) = mulRegex.find(this)!!.destructured
        return a.toInt() * b.toInt()
    }

    private fun String.getMulsFromString(): List<String> {
        return mulRegex.findAll(this).map { it.value }.toList()
    }

    fun part01(pathToInput: String = PATH_TO_INPUT) {
        val input = Path(pathToInput).readText()
        val muls = input.getMulsFromString()
        val result = muls.sumOf { it.solveOneMul() }
        println(result)
    }

    private fun findMostRecentDoOrDont(input: String, currentIndex: Int): String {
        val inputBeforeCurrentIndex = input.substring(0, currentIndex)
        val lastDoIndex = inputBeforeCurrentIndex.lastIndexOf("do()")
        val lastDontIndex = inputBeforeCurrentIndex.lastIndexOf("don't()")
        return if (lastDoIndex == -1 && lastDontIndex == -1) {
            ""
        } else {
            if (lastDoIndex > lastDontIndex) {
                "do"
            } else {
                "dont"
            }
        }
    }

    fun part02(pathToInput: String = PATH_TO_INPUT): Int {
        val input = Path(pathToInput).readText()
        val muls = mulRegex.findAll(input).map { it.value }.toList()
        var result = 0
        muls.forEach { mul ->
            val doOrDont = findMostRecentDoOrDont(input, input.indexOf(mul))
            if (doOrDont != "dont") {
                result += mul.solveOneMul()
            }
        }
        return result
    }
}
