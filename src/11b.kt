package task11b

fun main(args: Array<String>) {
    val serial = 8979

    val m = Array(300) { y ->
        IntArray(300) { x ->
            ((x + 11) * (y + 1) + serial) * (x + 11) % 1000 / 100 - 5
        }
    }

    val sums = Array(300) { IntArray(301) }
    for (y in 0..299) {
        sums[y][0] = m[y][0]
        for (x in 1..300) {
            sums[y][x] = sums[y][x - 1] + m[y][x - 1]
        }
    }

    var max = 0
    var mx = -1
    var my = -1
    var ms = -1
    for (s in 1..300) {
        for (y in 0..(300 - s)) {
            for (x in 0..(300 - s)) {
                var sum = 0
                for (dy in 0..(s-1)) {
                    sum += sums[y + dy][x + s] - sums[y + dy][x]
                }
                if (sum > max) {
                    max = sum
                    mx = x + 1
                    my = y + 1
                    ms = s
                }
            }
        }
    }
    println("$mx,$my,$ms")
    println("$max")
}