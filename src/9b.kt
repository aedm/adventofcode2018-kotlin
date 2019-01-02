package task9b

import kotlin.system.measureTimeMillis

data class Link(val value: Long, var next: Link?, var prev: Link?)

fun addMarble(m: MutableList<Link>, link: Link, value: Long): Link {
    val new = Link(value, link, link.prev)
    m.add(new)
    link.prev!!.next = new
    link.prev = new
    return new
}

fun removeMarble(link: Link) {
    link.next!!.prev = link.prev
    link.prev!!.next = link.next
}

fun solve() {
    val players = 459
    val lastMarble = 7132000

    val m = mutableListOf(Link(0, null, null))
    m[0].prev = m[0]
    m[0].next = m[0]
    val points = LongArray(players)
    var currentLink = m[0]
    for (i in 1..lastMarble) {
        if (i % 23 == 0) {
            currentLink = currentLink.prev!!.prev!!.prev!!.prev!!.prev!!.prev!!
            val takeLink = currentLink.prev!!
            points[i % players] += takeLink.value + i.toLong()
            removeMarble(takeLink)
        } else {
            currentLink = addMarble(m, currentLink.next!!.next!!, i.toLong())
        }
    }
    println(points.max())
}

fun main(args: Array<String>) {
    val x = measureTimeMillis { solve() }
    println("Runtime: $x ms")
}