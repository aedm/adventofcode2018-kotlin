package task7a

import java.io.File

class Node(val name: String) {
    val ins = mutableSetOf<Node>()
    val outs = mutableSetOf<Node>()
}

fun main(args: Array<String>) {
    val lines = File("inputs/7.txt").readLines()
    val regex = Regex("Step (\\w*) must be finished before step (\\w*) can begin.")

    val nodes = mutableMapOf<String, Node>()
    for (line in lines) {
        val x = regex.find(line)!!.groupValues
        val n1 = nodes.getOrPut(x[1]) { Node(x[1]) }
        val n2 = nodes.getOrPut(x[2]) { Node(x[2]) }
        n1.outs.add(n2)
        n2.ins.add(n1)
    }

    val sources = nodes.values.filter { it.ins.size == 0 }.toMutableList()
    val result = mutableListOf<Node>()
    while (!sources.isEmpty()) {
        sources.sortBy { it.name }
        for (node in sources[0].outs) {
            node.ins.remove(sources[0])
            if (node.ins.isEmpty()) sources.add(node)
        }
        result.add(sources[0])
        sources.removeAt(0)
    }

    println(result.joinToString("") { it.name })
}