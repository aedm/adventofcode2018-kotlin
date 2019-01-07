package task14a

fun check(m: List<Int>, input: Int): Boolean {
    var i = m.size - 1
    var x = input
    while (x > 0) {
        if (i < 0 || m[i] != x % 10) return false
        i--
        x /= 10
    }
    println(i + 1)
    return true
}

fun main(args: Array<String>) {
    var a = 0
    var b = 1
    val m = mutableListOf(3, 7)
    val input = 323081

    while (true) {
        val c = m[a] + m[b]
        if (c >= 10) {
            m.add(1)
            if (check(m, input)) return
        }
        m.add(c % 10)
        if (check(m, input)) return
        a = (a + 1 + m[a]) % m.size
        b = (b + 1 + m[b]) % m.size
    }
}