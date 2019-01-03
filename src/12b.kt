package task12a

import java.io.File

fun main(args: Array<String>) {
    val lines = File("inputs/12.txt").readLines()

    val generations = 1000
    val start = generations * 2

    val init = lines[0].replace("initial state: ", "")
    var m = BooleanArray(start * 2 + init.length)
    init.forEachIndexed { index, c -> m[start + index] = c == '#' }

    val rules = BooleanArray(32)
    lines.drop(2).forEach {
        val s = it.split(" => ")
        val rule = s[0].fold(0) { acc, c -> (acc shl 1) + if (c == '#') 1 else 0 }
        rules[rule] = s[1] == "#"
    }

    for (g in 1..generations) {
        var next = BooleanArray(start * 2 + init.length)
        for (x in 2..(m.size - 3)) {
            val rule = (0..4).sumBy { (16 shr it) * (if (m[x - 2 + it]) 1 else 0) }
            next[x] = rules[rule]
        }
        m = next
        var res = 0
        for (x in 0 until m.size) {
            if (m[x]) res += x - start
        }

        println("$g: $res")
        println(m.map{if (it) '#' else '.'}.joinToString("").trim('.'))
    }

    println((50000000000L - 1000L) * 22L + 22475L)
}