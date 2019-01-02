package task10a

import java.io.File
import kotlin.system.measureTimeMillis

class Dot(val startx: Int, val starty: Int, val dx: Int, val dy: Int) {
    var x = startx
    var y = starty
}

fun solve() {
    val lines = File("inputs/10.txt").readLines()
    val regex2 = Regex("position=<\\s*(-?\\d*),\\s*(-?\\d*)> velocity=<\\s*(-?\\d*),\\s*(-?\\d*)>")

    val dots = mutableListOf<Dot>()
    for (line in lines) {
        val x = regex2.find(line)!!.groupValues.drop(1).map { it.toInt() }
        dots.add(Dot(x[0], x[1], x[2], x[3]))
    }

    var steps = 0
    var d: Int? = null
    while (true) {
        val minx = dots.minBy { it.x }
        val miny = dots.minBy { it.y }
        val maxx = dots.maxBy { it.x }
        val maxy = dots.maxBy { it.y }
        val nextd = maxx!!.x - minx!!.x + maxy!!.y - miny!!.y
        if (d !== null && nextd > d) break
        d = nextd
        steps++
        dots.forEach{
            it.x += it.dx
            it.y += it.dy
        }
    }

    steps--
    dots.forEach {
        it.x = it.startx + steps * it.dx
        it.y = it.starty + steps * it.dy
    }

    val minx = dots.minBy { it.x }!!.x
    val miny = dots.minBy { it.y }!!.y
    val maxx = dots.maxBy { it.x }!!.x
    val maxy = dots.maxBy { it.y }!!.y

    val dx = maxx - minx + 1
    val dy = maxy - miny + 1

    val a = BooleanArray(dx * dy)
    dots.forEach {
        a[(it.x - minx) + dx * (it.y - miny)] = true
    }

    for (y in miny..maxy) {
        var s = ""
        for (x in minx..maxx) {
            s += if (a[(y-miny) * dx + (x-minx)]) 'X' else '.'
        }
        println(s)
    }
    println(steps)
}

fun main(args: Array<String>) {
    val x = measureTimeMillis { solve() }
    println("Runtime: $x ms")
}