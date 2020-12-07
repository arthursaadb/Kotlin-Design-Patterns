package behavioral

import org.junit.jupiter.api.Test

class Printer(val stringFormatter: (String) -> String) {
    fun printString(message: String) {
        println(stringFormatter(message))
    }
}

val lowerCaseFormatter = { it: String -> it.toLowerCase() }
val upperCaseFormatter = { it: String -> it.toUpperCase() }

class StrategyTest() {
    @Test
    fun `test strategy patter`(){
        val inputString = "LOREM ipsum DOLOR sit amet"

        val lowerCasePrinter = Printer(lowerCaseFormatter)
        lowerCasePrinter.stringFormatter

        val uperCasePrinter = Printer(upperCaseFormatter)
        uperCasePrinter.stringFormatter
    }
}