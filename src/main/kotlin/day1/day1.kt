package day1

import loadFile

fun main() {
    val data = "/day1/input.txt".loadFile().map(String::toInt)
    part1(data)
    part2(data)
}

private fun part1(data: List<Int>) {
    val combined = data.flatMapIndexed { index, i -> data.drop(index).map { Pair(i, it) } }
    val result = combined.first { it.first + it.second == 2020 }
    println("part1 is $result -> ${result.first * result.second}")
}

private fun part2(data: List<Int>) {
    val combined = data.flatMapIndexed { index, i1 ->
        data.drop(index).flatMapIndexed { index2, i2 ->
            data.drop(index2).map { Triple(i1, i2, it) }
        }
    }
    val result = combined.first { it.first + it.second + it.third == 2020 }
    println("part2 is $result -> ${result.first * result.second * result.third}")
}