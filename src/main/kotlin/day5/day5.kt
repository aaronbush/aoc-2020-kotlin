package day5

import loadFile

fun F(num: Pair<Int, Int>): Pair<Int, Int> = num.first to num.second - (num.second - num.first) / 2 - 1
fun B(num: Pair<Int, Int>): Pair<Int, Int> = num.first + (num.second - num.first) / 2 + 1 to num.second


fun main() {
    val data = "/day5/input.txt".loadFile()
    part1(data)
    part2(data)
}

private fun getSeatNumbers(data: List<String>): List<Int> {
    val funMap = mapOf('F' to ::F, 'B' to ::B, 'L' to ::F, 'R' to ::B)
    val seats = data.map { s ->
        val row = s.take(7).fold(0 to 127) { acc, c -> funMap[c]?.invoke(acc) ?: acc }
        val col = s.takeLast(3).fold(0 to 7) { acc, c -> funMap[c]?.invoke(acc) ?: acc }
        Pair(row.first, col.first)
    }
    return seats.map { it.first * 8 + it.second }
}

fun part1(data: List<String>) {
    val result = getSeatNumbers(data).maxOrNull()
    println("part 1 is $result")
}

fun part2(data: List<String>) {
    val sortedSeats = getSeatNumbers(data).sorted()
    val result = sortedSeats.zipWithNext { a, b -> b - a }.indexOf(2)
    println("part 2 is ${sortedSeats[result]} -*- ${sortedSeats[result + 1]}")
}
