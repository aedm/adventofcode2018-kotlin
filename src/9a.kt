package task9a

fun main(args: Array<String>) {
    val players = 459
    val lastMarble = 71320

    val m = mutableListOf(0)
    val points = IntArray(players)
    var currentIndex = 0
    for (i in 1..lastMarble) {
        if (i % 23 == 0) {
            val takeIndex = (currentIndex - 7 + m.size) % m.size
            points[i % players] += m[takeIndex] + i
            m.removeAt(takeIndex)
            currentIndex = takeIndex % m.size
        } else {
            currentIndex = (currentIndex + 2) % m.size
            m.add(currentIndex, i)
        }
    }

    println(points.max())
}