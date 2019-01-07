package task13b

import java.io.File

enum class FieldType {
    EMPTY,
    HORIZONTAL,
    VERTICAL,
    UPLEFT,
    UPRIGHT,
    CROSS
}

class Car(var x: Int, var y: Int, var dx: Int, var dy: Int) {
    var turn = 0
    var crashed = false
}

class Field(val type: FieldType, var car: Car? = null)

fun main(args: Array<String>) {
    val lines = File("inputs/13.txt").readLines()

    val m = lines.mapIndexed { y, line ->
        line.mapIndexed { x, c ->
            when (c) {
                ' ' -> Field(FieldType.EMPTY)
                '/' -> Field(FieldType.UPRIGHT)
                '\\' -> Field(FieldType.UPLEFT)
                '+' -> Field(FieldType.CROSS)
                '-' -> Field(FieldType.HORIZONTAL)
                '>' -> Field(FieldType.HORIZONTAL, Car(x, y, 1, 0))
                '<' -> Field(FieldType.HORIZONTAL, Car(x, y, -1, 0))
                '|' -> Field(FieldType.VERTICAL)
                '^' -> Field(FieldType.VERTICAL, Car(x, y, 0, -1))
                'v' -> Field(FieldType.VERTICAL, Car(x, y, 0, 1))
                else -> throw Error()
            }
        }.toList()
    }.toList()

    val cars = m.flatten().map { it.car }.filterNotNull().toMutableList()

    val sx = lines.map { it.length }.max()!!

    while (true) {
        cars.forEach { if (it.crashed) m[it.y][it.x].car = null }
        cars.removeAll { it.crashed }
        if (cars.size == 1) {
            val c = cars[0]
            println("${c.x},${c.y}")
            return
        }
        cars.sortBy { it.y * sx + it.x }
        cars.forEach {
            if (it.crashed) return@forEach
            m[it.y][it.x].car = null
            val nx = it.x + it.dx
            val ny = it.y + it.dy
            val f = m[ny][nx]
            if (f.car != null) {
                f.car!!.crashed = true
                it.crashed = true
                return@forEach
            }

            it.x = nx
            it.y = ny
            f.car = it

            when (f.type) {
                FieldType.UPRIGHT -> {
                    val ox = it.dx
                    val oy = it.dy
                    it.dx = -oy
                    it.dy = -ox
                }
                FieldType.UPLEFT -> {
                    val ox = it.dx
                    val oy = it.dy
                    it.dx = oy
                    it.dy = ox
                }
                FieldType.CROSS -> {
                    val ox = it.dx
                    val oy = it.dy
                    when (it.turn) {
                        0 -> {
                            it.dx = oy
                            it.dy = -ox
                        }
                        2 -> {
                            it.dx = -oy
                            it.dy = ox
                        }
                        else -> {
                        }
                    }
                    it.turn = (it.turn + 1) % 3
                }
                FieldType.VERTICAL -> {
                    if (it.dx != 0) throw Error()
                }
                FieldType.HORIZONTAL -> {
                    if (it.dy != 0) throw Error()
                }
                else -> {
                }
            }
        }
    }
}