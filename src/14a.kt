package task14a

fun main(args: Array<String>) {
    var a = 0
    var b = 1
    val m = mutableListOf(3, 7)
    val input = 323081

    while (m.size < input + 10) {
        val c = m[a] + m[b]
        if (c >= 10) m.add(1)
        m.add(c % 10)
        a = (a + 1 + m[a]) % m.size
        b = (b + 1 + m[b]) % m.size
    }
    println(m.drop(input).take(10).joinToString("") { it.toString() })
}