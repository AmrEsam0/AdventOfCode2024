package day03

import kotlin.io.path.Path
import kotlin.io.path.readText

object Day03 {
    private const val PATH_TO_INPUT_01 = "src/day03/input.txt"

    private val mulRegex = """mul\((\d{1,3}),(\d{1,3})\)""".toRegex()
    private fun String.solveOneMul(): Int {
        val (a, b) = mulRegex.find(this)!!.destructured
        return a.toInt() * b.toInt()
    }

    private fun String.getMulsFromString(): List<String> {
        return mulRegex.findAll(this).map { it.value }.toList()
    }

    fun part01(pathToInput: String = PATH_TO_INPUT_01) {
        val input = Path(pathToInput).readText()
        val muls = input.getMulsFromString()
        val result = muls.sumOf { it.solveOneMul() }
        println(result)
    }
}
