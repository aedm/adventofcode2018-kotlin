package task8a

import java.io.File

data class Data(val end: Int, val sum: Int)

fun sumMeta(nums: List<Int>, start: Int): Data {
    val childCount = nums[start]
    val metaCount = nums[start + 1]
    var sum = 0
    var end = start + 2
    for (i in 1..childCount) {
        val r = sumMeta(nums, end)
        sum += r.sum
        end = r.end
    }
    for (i in 0..(metaCount - 1)) sum += nums[end + i]
    return Data(end + metaCount, sum)
}

fun main(args: Array<String>) {
    val nums = File("inputs/8.txt").readLines()[0].split(' ').map { it.toInt() }
    val r = sumMeta(nums, 0)
    println(r.sum)
}