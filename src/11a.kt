package task11a

fun main(args: Array<String>) {
    val serial = 8979

    val m = Array(300) { y ->
        IntArray(300) { x ->
            ((x + 11) * (y + 1) + serial) * (x + 11) % 1000 / 100 - 5
        }
    }

    var max = 0
    var mx = -1
    var my = -1
    for (y in 0..297) {
        for (x in 0..297) {
            val sum = (0..8).sumBy { m[y + it / 3][x + it % 3] }
            if (sum > max) {
                max = sum
                mx = x+1
                my = y+1
            }
        }
    }
    println("$mx,$my")
}