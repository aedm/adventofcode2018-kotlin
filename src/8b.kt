package task8b

import java.io.File

data class Data(val end: Int, val sum: Int)

fun sumMeta(nums: List<Int>, start: Int): Data {
    val childCount = nums[start]
    val metaCount = nums[start + 1]
    if (childCount == 0) {
        var sum = 0
        for (i in 0..(metaCount - 1)) sum += nums[start + 2 + i]
        return Data(start + 2 + metaCount, sum)
    }

    val sums = mutableListOf<Int>()
    var end = start + 2
    for (i in 1..childCount) {
        val r = sumMeta(nums, end)
        sums.add(r.sum)
        end = r.end
    }

    var sum = 0
    for (i in 0..(metaCount - 1)) {
        val child = nums[end + i] - 1
        if (child < 0 || child >= childCount) continue
        sum += sums[child]
    }
    return Data(end + metaCount, sum)
}

fun main(args: Array<String>) {
    val nums = File("inputs/8.txt").readLines()[0].split(' ').map { it.toInt() }
    val r = sumMeta(nums, 0)
    println(r.sum)
}